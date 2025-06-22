package com.kavindu.fabrikza.cart.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDTO {
    private int cartItemId;
    private UUID productId;
    private String productName;
    private String colorName;
    private String sizeLabel;
    private int quantity;
    private double price;
    private String imageUrl;

}
