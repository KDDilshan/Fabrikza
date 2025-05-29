package com.kavindu.fabrikza.product.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    private String description;

    private Double price;

    private String manufacturer;

    private String imageName;

    private Double discountPercentage;

    private Date discountStartDate;

    private Date discountEndDate;



    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

    @OneToMany(mappedBy = "product")
    private List<ProductSize> sizes;

    @OneToMany(mappedBy = "product")
    private List<ProductColor> colors;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

}
