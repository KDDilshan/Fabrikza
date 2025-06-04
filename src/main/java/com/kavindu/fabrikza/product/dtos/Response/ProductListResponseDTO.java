package com.kavindu.fabrikza.product.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductListResponseDTO {
    private UUID id;
    private String name;
    private Double price;
    private String thumbnail;
    private String categoryName;
}
