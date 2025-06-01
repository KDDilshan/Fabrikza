package com.kavindu.fabrikza.product.dtos.Response;

import com.kavindu.fabrikza.product.dtos.Request.ProductImageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductColorResponseDTO {
    private Integer colorId;
    private String colorName;
    private String hexCode;
    private List<ProductImageDTO> images;
    private List<ProductVariantResponseDTO> variants;
}
