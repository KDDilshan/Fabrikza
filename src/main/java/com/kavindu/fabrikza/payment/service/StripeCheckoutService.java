package com.kavindu.fabrikza.payment.service;

import com.kavindu.fabrikza.payment.Repository.OrderRepository;
import com.kavindu.fabrikza.payment.models.Orders;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StripeCheckoutService {
    @Value("${stripe.secret.key}")
    private String secretKey;

    private final OrderRepository orderRepository;

    public StripeCheckoutService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Map<String, String> createCheckoutSession(Map<String, Object> data) {
        Stripe.apiKey = secretKey;

        String orderIdStr = data.get("orderId").toString();
        UUID orderId = UUID.fromString(orderIdStr);

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:3000/success?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl("http://localhost:3000/cancel")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setUnitAmount((long) (order.getTotalAmount() * 100))
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Order #" + order.getId())
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .putMetadata("order_id", orderIdStr)
                    .build();

            Session session = Session.create(params);

            Map<String, String> response = new HashMap<>();
            response.put("checkoutUrl", session.getUrl());
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Stripe Checkout Session creation failed", e);
        }
    }
}
