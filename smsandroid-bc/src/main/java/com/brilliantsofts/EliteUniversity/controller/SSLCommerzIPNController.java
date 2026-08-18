package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.entity.Payment;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import com.brilliantsofts.EliteUniversity.repository.PaymentRepository;
import com.brilliantsofts.EliteUniversity.service.SSLCommerzService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/payments/ipn")
@RequiredArgsConstructor
public class SSLCommerzIPNController {

    private final PaymentRepository paymentRepository;
    private final SSLCommerzService sslCommerzService;

    @PostMapping
    public ResponseEntity<Map<String, String>> handleIPN(@RequestParam Map<String, String> params) {
        String tranId = params.get("tran_id");
        String status = params.get("status");
        log.info("IPN received: tran_id={}, status={}", tranId, status);

        Map<String, String> response = new HashMap<>();

        if (tranId == null || tranId.isEmpty()) {
            response.put("status", "ERROR");
            response.put("message", "Missing tran_id");
            return ResponseEntity.badRequest().body(response);
        }

        Payment payment = paymentRepository.findByTransactionId(tranId);
        if (payment == null) {
            log.warn("IPN: Payment not found for tran_id={}", tranId);
            response.put("status", "ERROR");
            response.put("message", "Payment not found");
            return ResponseEntity.ok(response);
        }

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            log.info("IPN: Payment already processed for tran_id={}", tranId);
            response.put("status", "OK");
            response.put("message", "Already processed");
            return ResponseEntity.ok(response);
        }

        boolean validated = sslCommerzService.validateTransaction(tranId);
        if (validated) {
            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setGatewayResponse(params.toString());
            paymentRepository.save(payment);
            log.info("IPN: Payment validated and marked SUCCESS for tran_id={}", tranId);
            response.put("status", "OK");
            response.put("message", "Payment verified successfully");
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setGatewayResponse(params.toString());
            paymentRepository.save(payment);
            log.warn("IPN: Payment validation failed for tran_id={}", tranId);
            response.put("status", "FAILED");
            response.put("message", "Payment verification failed");
        }

        return ResponseEntity.ok(response);
    }
}
