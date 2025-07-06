package com.kavindu.fabrikza.payment.Repository;

import com.kavindu.fabrikza.payment.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
