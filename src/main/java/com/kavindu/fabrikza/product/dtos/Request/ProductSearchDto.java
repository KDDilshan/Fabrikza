package com.kavindu.fabrikza.product.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDto {
    private String name;
    private String manufacturer;
    private Double minPrice;
    private Double maxPrice;
    private Long categoryId;
}
