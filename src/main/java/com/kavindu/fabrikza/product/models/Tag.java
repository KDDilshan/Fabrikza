package com.kavindu.fabrikza.product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tag {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Product> products;
}
