package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.PaymentRequest;
import com.nhomA.mockproject.service.PaypalService;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaypalController {

    final private PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/make-payment")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            String paymentApprovalLink = paypalService.createPayment(paymentRequest);
            return ResponseEntity.ok(paymentApprovalLink);
        } catch ( PayPalRESTException e) {
            return ResponseEntity.status(500).body("Error processing payment");
        }
    }

    @GetMapping("/complete-payment")
    public ResponseEntity<String> completePayment(@RequestParam("paymentId") String paymentId,
                                                  @RequestParam("payerId") String payerId) {
        try {
            String paymentStatus = paypalService.completePayment(paymentId, payerId);
            return ResponseEntity.ok(paymentStatus);
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(500).body("Error completing payment");
        }
    }
}
