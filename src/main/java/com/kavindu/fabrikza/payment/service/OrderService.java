package com.kavindu.fabrikza.payment.service;

import com.kavindu.fabrikza.Authentication.models.AppUser;
import com.kavindu.fabrikza.Authentication.repositories.UserRepository;
import com.kavindu.fabrikza.payment.dto.OrderResponse;
import com.kavindu.fabrikza.product.repositories.ProductRepository;
import com.kavindu.fabrikza.product.models.Product;
import com.kavindu.fabrikza.payment.Repository.OrderItemRepository;
import com.kavindu.fabrikza.payment.Repository.OrderRepository;
import com.kavindu.fabrikza.payment.Repository.ShippingDeatilsRepository;
import com.kavindu.fabrikza.payment.dto.OrderItemRequest;
import com.kavindu.fabrikza.payment.dto.OrderRequest;
import com.kavindu.fabrikza.payment.models.OrderItem;
import com.kavindu.fabrikza.payment.models.Orders;
import com.kavindu.fabrikza.payment.models.ShippingDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShippingDeatilsRepository shippingDeatilsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ShippingDeatilsRepository shippingDeatilsRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.shippingDeatilsRepository = shippingDeatilsRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

        public OrderResponse placeOrder(OrderRequest request, String username) {

            AppUser user=userRepository.findByEmail(username)
                    .orElseThrow(()->new RuntimeException("User not found"));

            Orders order=new Orders();
            order.setDate(new Date());
            order.setStatus("PENDING");

            List<OrderItem> orderItemsList=new ArrayList<>();
            double totalAmount = 0;

            for(OrderItemRequest itemRequest:request.getItems()){
                Product product=productRepository.findById(itemRequest.getProductId())
                        .orElseThrow(()->new RuntimeException("Product Not found"));

                OrderItem item=new OrderItem();
                item.setProduct(product);
                item.setQuantity(itemRequest.getQuantity());
                item.setPrice(product.getPrice() * itemRequest.getQuantity());
                item.setOrder(order);

                orderItemsList.add(item);
                totalAmount += item.getPrice();
            }

            order.setTotalAmount(totalAmount);
            order.setUser(user);
            order.setOrderItemsList(orderItemsList);

            ShippingDetails shipping=new ShippingDetails();
            shipping.setShippingAddress(request.getShippingAddress());
            shipping.setPhoneNo(request.getPhoneNo());
            shipping.setOrder(order);
            shipping.setAppUser(user);
            order.setShippingDetails(shipping);


            orderRepository.save(order);

            OrderResponse orderResponse=new OrderResponse();
            orderResponse.setOrderID(order.getId());

            return orderResponse;
        }
    }
