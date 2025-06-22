package com.kavindu.fabrikza.product.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
    private UUID id;
    private String name;
    private String description;
    private List<String> images;
    private List<ProductVariantsDTO> variants;
}
