package com.kavindu.fabrikza.cart.controller;

import com.kavindu.fabrikza.cart.dto.request.AddToCartDto;
import com.kavindu.fabrikza.cart.dto.response.CartItemResponseDTO;
import com.kavindu.fabrikza.cart.model.CartItem;
import com.kavindu.fabrikza.cart.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart/v1")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDto dto) {
        cartService.addToCart(dto);
        return ResponseEntity.ok("Added to cart");
    }

    @GetMapping("/{userId}")
    public List<CartItemResponseDTO> getCart(@PathVariable int userId) {
        return cartService.getUserCart(userId);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeItem(@PathVariable int cartItemId) {
        cartService.removeItem(cartItemId);
        return ResponseEntity.ok("Item removed");
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable int userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared");
    }

}
