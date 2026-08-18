package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.config.SSLCommerzConfig;
import com.brilliantsofts.EliteUniversity.entity.Payment;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import com.brilliantsofts.EliteUniversity.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SSLCommerzService {

    private final SSLCommerzConfig config;
    private final PaymentRepository paymentRepository;

    public Map<String, Object> initiatePayment(Payment payment, String customerName, String customerEmail, String customerPhone) {
        String tranId = "TXN-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();

        Map<String, String> params = new LinkedHashMap<>();
        params.put("store_id", config.getStoreId());
        params.put("store_passwd", config.getStorePassword());
        params.put("total_amount", String.format(Locale.US, "%.2f", payment.getAmount()));
        params.put("currency", "BDT");
        params.put("tran_id", tranId);
        params.put("success_url", config.getSuccessUrl() + "?tran_id=" + tranId + "&status=VALID");
        params.put("fail_url", config.getFailUrl() + "?tran_id=" + tranId + "&status=FAILED");
        params.put("cancel_url", config.getCancelUrl() + "?tran_id=" + tranId + "&status=CANCELLED");
        params.put("ipn_url", config.getIpnUrl() + "?tran_id=" + tranId);
        params.put("emi_option", "0");
        params.put("cus_name", (customerName != null && !customerName.trim().isEmpty()) ? customerName : "Applicant");
        params.put("cus_email", (customerEmail != null && !customerEmail.trim().isEmpty()) ? customerEmail : "applicant@eliteuniversity.edu");
        params.put("cus_phone", (customerPhone != null && !customerPhone.trim().isEmpty()) ? customerPhone : "01700000000");
        params.put("cus_add1", "Dhaka");
        params.put("cus_city", "Dhaka");
        params.put("cus_state", "Dhaka");
        params.put("cus_postcode", "1000");
        params.put("cus_country", "Bangladesh");
        params.put("shipping_method", "NO");
        params.put("product_name", "Admission Fee");
        params.put("product_category", "Education");
        params.put("product_profile", "non-physical-goods");

        payment.setTransactionId(tranId);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setPaymentMethod(com.brilliantsofts.EliteUniversity.enums.PaymentMethod.ONLINE_BANKING);
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            StringBuilder formBody = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (formBody.length() > 0) formBody.append("&");
                formBody.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
                formBody.append("=");
                formBody.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            }

            HttpEntity<String> request = new HttpEntity<>(formBody.toString(), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(config.getApiUrl(), request, String.class);
            String responseBody = response.getBody();
            log.info("SSLCommerz initiate response: {}", responseBody);

            Map<String, Object> result = new HashMap<>();
            result.put("tran_id", tranId);
            result.put("status", "INITIATED");

            String status = extractJsonField(responseBody, "status");
            if (!"SUCCESS".equalsIgnoreCase(status)) {
                String failedReason = extractJsonField(responseBody, "failedreason");
                payment.setStatus(PaymentStatus.FAILED);
                payment.setNotes(failedReason);
                paymentRepository.save(payment);
                throw new RuntimeException("SSLCommerz initiation failed: " + (failedReason != null ? failedReason : "Unknown error"));
            }

            String sessionKey = extractJsonField(responseBody, "sessionkey");
            String gatewayUrl = extractJsonField(responseBody, "redirectGatewayURL");

            if (gatewayUrl != null && gatewayUrl.endsWith("cardname=")) {
                gatewayUrl = gatewayUrl + "bkash";
            }
            if (gatewayUrl == null || gatewayUrl.isEmpty()) {
                gatewayUrl = extractJsonField(responseBody, "GatewayPageURL");
            }
            if (gatewayUrl == null || gatewayUrl.isEmpty()) {
                gatewayUrl = getGatewayUrl(sessionKey);
            }

            result.put("session_key", sessionKey);
            result.put("gateway_url", gatewayUrl);

            return result;
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            log.error("SSLCommerz initiation failed: {}", e.getMessage());
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            throw new RuntimeException("Payment initiation failed: " + e.getMessage());
        }
    }

    public boolean validateTransaction(String tranId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            String body = "store_id=" + config.getStoreId() +
                    "&store_passwd=" + config.getStorePassword() +
                    "&tran_id=" + tranId;

            HttpEntity<String> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(config.getValidationUrl(), request, String.class);
            log.info("SSLCommerz validation response for {}: {}", tranId, response.getBody());

            String status = extractJsonField(response.getBody(), "status");
            if ("VALID".equalsIgnoreCase(status)) {
                Payment payment = paymentRepository.findByTransactionId(tranId);
                if (payment != null) {
                    payment.setStatus(PaymentStatus.SUCCESS);
                    paymentRepository.save(payment);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("SSLCommerz validation failed: {}", e.getMessage());
            return false;
        }
    }

    private String extractJsonField(String responseBody, String field) {
        if (responseBody == null || field == null) return null;
        String token = "\"" + field + "\"";
        int idx = responseBody.indexOf(token);
        if (idx < 0) return null;
        int colon = responseBody.indexOf(":", idx + token.length());
        if (colon < 0) return null;
        int start = responseBody.indexOf("\"", colon);
        if (start < 0) return null;
        int end = responseBody.indexOf("\"", start + 1);
        if (end < 0) return null;
        String val = responseBody.substring(start + 1, end);
        return val.replace("\\/", "/");
    }

    private String getGatewayUrl(String sessionKey) {
        String base = config.isSandbox()
                ? "https://sandbox.sslcommerz.com/gwprocess/v4/gw.php"
                : "https://securepay.sslcommerz.com/gwprocess/v4/gw.php";
        return base + "?Q=" + sessionKey;
    }
}
