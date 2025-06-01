package com.kavindu.fabrikza.product.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorDTO {
    private String colorName;
    private String hexCode;
    private List<ProductImageDTO> images;
    private List<ProductVariantDTO> variants;
}
