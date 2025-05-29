package com.kavindu.fabrikza.product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSize {
    @Id
    @GeneratedValue
    private int id;


    private String sizeLabel;
    private Integer quantity;

    @ManyToOne
    private Product product;
}
