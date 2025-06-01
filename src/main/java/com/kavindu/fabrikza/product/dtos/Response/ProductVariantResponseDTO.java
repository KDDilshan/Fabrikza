package com.kavindu.fabrikza.product.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantResponseDTO {
    private Integer variantId;
    private String sizeLabel;
    private Integer quantity;
}
