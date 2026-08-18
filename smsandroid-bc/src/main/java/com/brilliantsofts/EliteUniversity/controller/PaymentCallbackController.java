package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.entity.Payment;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import com.brilliantsofts.EliteUniversity.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentCallbackController {

    private final PaymentRepository paymentRepository;

    @RequestMapping(
        value = "/callback",
        method = {RequestMethod.GET, RequestMethod.POST},
        consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE}
    )
    public ResponseEntity<Void> handleCallback(@RequestParam Map<String, String> params) {
        String tranId = params.get("tran_id");
        String status = params.get("status");

        log.info("SSLCommerz callback received: tran_id={}, status={}, params={}", tranId, status, params);

        String angularCallbackUrl = "http://localhost:4200/pre-admission/payment/callback?status=FAILED";

        if (tranId != null && !tranId.isEmpty()) {
            Payment payment = paymentRepository.findByTransactionId(tranId);
            if (payment != null) {
                if ("VALID".equalsIgnoreCase(status) || "SUCCESS".equalsIgnoreCase(status) || "VALIDATED".equalsIgnoreCase(status)) {
                    payment.setStatus(PaymentStatus.SUCCESS);
                    payment.setGatewayResponse(params.toString());
                    paymentRepository.save(payment);
                    angularCallbackUrl = "http://localhost:4200/pre-admission/payment/callback?status=VALID&tran_id=" + tranId + "&amount=" + payment.getAmount();
                } else if ("CANCELLED".equalsIgnoreCase(status)) {
                    payment.setStatus(PaymentStatus.FAILED);
                    paymentRepository.save(payment);
                    angularCallbackUrl = "http://localhost:4200/pre-admission/payment/callback?status=CANCELLED&tran_id=" + tranId;
                } else {
                    payment.setStatus(PaymentStatus.FAILED);
                    paymentRepository.save(payment);
                    angularCallbackUrl = "http://localhost:4200/pre-admission/payment/callback?status=FAILED&tran_id=" + tranId;
                }
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(angularCallbackUrl));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
