package com.kavindu.fabrikza.cart.services;

import com.kavindu.fabrikza.Authentication.models.AppUser;
import com.kavindu.fabrikza.Authentication.repositories.UserRepository;
import com.kavindu.fabrikza.cart.dto.request.AddToCartDto;
import com.kavindu.fabrikza.cart.model.Cart;
import com.kavindu.fabrikza.cart.model.CartItem;
import com.kavindu.fabrikza.cart.repositories.CartItemRepository;
import com.kavindu.fabrikza.cart.repositories.CartRepository;
import com.kavindu.fabrikza.product.models.Color;
import com.kavindu.fabrikza.product.models.Product;
import com.kavindu.fabrikza.product.models.Size;
import com.kavindu.fabrikza.product.repositories.ProductColorRepository;
import com.kavindu.fabrikza.product.repositories.ProductRepository;
import com.kavindu.fabrikza.product.repositories.ProductSizeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductColorRepository colorRepository;
    private final ProductSizeRepository sizeRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository, ProductRepository productRepository, ProductColorRepository colorRepository, ProductSizeRepository sizeRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;

    }


    public void addToCart(AddToCartDto dto) {
        AppUser user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Color color = colorRepository.findById(dto.getColorId())
                .orElseThrow(() -> new RuntimeException("Color not found"));

        Size size = sizeRepository.findById(dto.getSizeId())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Optional<CartItem> existingItem = cartItemRepository
                .findByCartIdAndProductIdAndColorIdAndSizeId(cart.getId(), product.getId(), color.getId(), size.getId());

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + dto.getQuantity());
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setColor(color);
            item.setSize(size);
            item.setQuantity(dto.getQuantity());
            cart.getItems().add(item);
        }

        cartRepository.save(cart);
    }

    public List<CartItem> getUserCart(int userId) {
        return cartRepository.findByUserId(userId)
                .map(Cart::getItems)
                .orElse(Collections.emptyList());
    }


    public void removeItem(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void clearCart(int userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().clear();
        cartRepository.save(cart);
    }


}
