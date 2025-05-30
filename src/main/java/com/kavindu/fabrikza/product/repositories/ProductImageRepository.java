package com.kavindu.fabrikza.product.repositories;

import com.kavindu.fabrikza.product.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
}
