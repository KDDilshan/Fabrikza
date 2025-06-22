package com.kavindu.fabrikza.product.dtos.Response;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantsDTO {
    private String color;
    private String size;
    private Double price;
    private Integer quantity;
}
