package com.kavindu.fabrikza.product.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String manufacturer;
    private String categoryName;
    private Date createdAt;
    private Date updatedAt;
    private List<ProductColorResponseDTO> colors;
}
