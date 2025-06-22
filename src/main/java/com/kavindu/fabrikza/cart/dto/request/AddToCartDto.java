package com.kavindu.fabrikza.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartDto {
    private int userId;
    private UUID productId;
    private int colorId;
    private int sizeId;
    private int quantity;
}
