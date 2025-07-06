package com.kavindu.fabrikza.payment.service;

import com.kavindu.fabrikza.payment.Repository.OrderRepository;
import com.kavindu.fabrikza.payment.Repository.PaymentRepository;
import com.kavindu.fabrikza.payment.dto.PaymentRequest;
import com.kavindu.fabrikza.payment.models.Orders;
import com.kavindu.fabrikza.payment.models.Payment;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StripePaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final EmailService emailService;

    public StripePaymentService(OrderRepository orderRepository, PaymentRepository paymentRepository, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.emailService = emailService;
    }

    public Map<String, String> createPaymentIntent(Map<String, Object> data) throws StripeException {
        String orderIdStr = data.get("orderId").toString();
        UUID orderId = UUID.fromString(orderIdStr);

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        long amount = (long) (order.getTotalAmount() * 100); // Stripe uses cents

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency("usd")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .putMetadata("orderId", orderId.toString())
                .build();

        PaymentIntent intent = PaymentIntent.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", intent.getClientSecret());
        return response;
    }

}
