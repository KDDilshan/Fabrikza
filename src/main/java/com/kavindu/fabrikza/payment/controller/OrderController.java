package com.kavindu.fabrikza.payment.controller;

import com.kavindu.fabrikza.payment.dto.OrderRequest;
import com.kavindu.fabrikza.payment.dto.OrderResponse;
import com.kavindu.fabrikza.payment.models.Orders;
import com.kavindu.fabrikza.payment.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders/v1/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> PlaceOrder(@RequestBody OrderRequest request, Principal principal){
        System.out.println(principal.getName());
        OrderResponse orders=orderService.placeOrder(request,principal.getName());
        return ResponseEntity.ok(orders);
    }
}
