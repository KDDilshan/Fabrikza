package com.kavindu.fabrikza.product.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDto {
    private String name;
    private String description;
    private Double price;
    private String manufacturer;
    private Integer categoryId;
}
