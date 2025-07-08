package com.kavindu.fabrikza.payment.controller;

import com.kavindu.fabrikza.payment.service.StripeCheckoutService;
import com.kavindu.fabrikza.payment.service.StripePaymentService;
import com.kavindu.fabrikza.payment.service.StripeWebhookService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment/v1")
public class StripeController {

    private final StripeWebhookService stripeWebhookService;
    private final StripeCheckoutService stripeCheckoutService;

    public StripeController(StripeWebhookService stripeWebhookService, StripeCheckoutService stripeCheckoutService) {
        this.stripeWebhookService = stripeWebhookService;
        this.stripeCheckoutService = stripeCheckoutService;
    }


    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(HttpServletRequest request) {
        return stripeWebhookService.handleStripeWebhook(request);
    }


    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody Map<String, Object> data) {
        Map<String, String> response = stripeCheckoutService.createCheckoutSession(data);
        return ResponseEntity.ok(response);
    }
}
