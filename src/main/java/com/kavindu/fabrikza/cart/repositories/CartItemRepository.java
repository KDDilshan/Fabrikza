package com.kavindu.fabrikza.cart.repositories;

import com.kavindu.fabrikza.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    Optional<CartItem> findByCartIdAndProductIdAndColorIdAndSizeId(int cartId, UUID productId, int colorId, int sizeId);
}
