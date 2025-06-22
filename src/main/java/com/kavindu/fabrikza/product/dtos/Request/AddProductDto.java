package com.kavindu.fabrikza.product.dtos.Request;

import com.kavindu.fabrikza.product.models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductDto {
    private String name;
    private String description;
    private Double price;
    private String manufacturer;
    private int categoryId;
    private List<ProductColorDTO> colors=new ArrayList<>();
}


