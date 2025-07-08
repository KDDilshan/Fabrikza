package com.kavindu.fabrikza.payment.service;

import com.kavindu.fabrikza.payment.Repository.OrderRepository;
import com.kavindu.fabrikza.payment.Repository.PaymentRepository;
import com.kavindu.fabrikza.payment.dto.PaymentRequest;
import com.kavindu.fabrikza.payment.models.Orders;
import com.kavindu.fabrikza.payment.models.Payment;
import com.kavindu.fabrikza.payment.models.enums.PaymentStatus;
import com.kavindu.fabrikza.payment.models.enums.PaymentType;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

@Service
public class StripeWebhookService {

    @Value("${stripe.secret.key}")
    private String endpointSecret;


    private final PaymentRepository paymentRepository;
    private final EmailService emailService;
    private final OrderRepository orderRepository;

    public StripeWebhookService(PaymentRepository paymentRepository, EmailService emailService, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.emailService = emailService;
        this.orderRepository = orderRepository;
    }


    public ResponseEntity<String> handleStripeWebhook(HttpServletRequest request) {
        String payload = "";

        try (Scanner scanner = new Scanner(request.getInputStream(), "UTF-8")) {
            payload = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error reading webhook payload");
        }

        String sigHeader = request.getHeader("Stripe-Signature");
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Webhook signature verification failed");
        }

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer()
                    .getObject()
                    .orElse(null);

            if (session != null && session.getMetadata() != null) {
                String orderIdStr = session.getMetadata().get("order_id");
                if (orderIdStr != null) {
                    UUID orderId = UUID.fromString(orderIdStr);

                    PaymentRequest paymentRequest = new PaymentRequest();
                    paymentRequest.setOrderId(orderId);
                    paymentRequest.setPaymentMethod(PaymentType.STRIPE);
                    paymentRequest.setPaymentStatus(PaymentStatus.SUCCESS);
                    paymentRequest.setTransactionId(session.getPaymentIntent());

                    try {
                        processPayment(paymentRequest);
                    } catch (Exception ex) {
                        // Log the exception here with your logger
                        return ResponseEntity.status(500).body("Error processing payment");
                    }
                }
            }
        }

        return ResponseEntity.ok("Webhook received");
    }

    public void processPayment(PaymentRequest request) {
        Orders order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setId(UUID.randomUUID());
        payment.setOrders(order);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus(request.getPaymentStatus());
        payment.setTransactionId(request.getTransactionId());

        paymentRepository.save(payment);

        order.setStatus("PAID");
        orderRepository.save(order);

        emailService.sendConfirmation(
                order.getUser().getEmail(),
                "Order Confirmed",
                "Thank you for your order. Your payment was successful"
        );
    }

}
