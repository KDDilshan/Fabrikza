package com.kavindu.fabrikza.product.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDto {
    private String name;
    private String description;
    private double price;
    private String manufacture;
    private Double discountPercentage;
    private String productSize;
    private int sizeQuantity;
    private String Color;

}
