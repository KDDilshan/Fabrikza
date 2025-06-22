package com.kavindu.fabrikza.product.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorDTO {
    private int colorId;
    private List<ProductImageDTO> images=new ArrayList<>();
    private List<ProductVariantDTO> variants=new ArrayList<>();
}
