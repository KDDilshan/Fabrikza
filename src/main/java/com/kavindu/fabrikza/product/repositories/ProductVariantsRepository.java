package com.kavindu.fabrikza.product.repositories;

import com.kavindu.fabrikza.product.models.Color;
import com.kavindu.fabrikza.product.models.Product;
import com.kavindu.fabrikza.product.models.ProductVariants;
import com.kavindu.fabrikza.product.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantsRepository extends JpaRepository<ProductVariants,Integer> {
    List<ProductVariants> findByProduct(Product product);
    List<ProductVariants> findByProductAndColor(Product product, Color color);
    Optional<ProductVariants> findByProductAndColorAndSize(Product product, Color color, Size size);
    List<ProductVariants> findByQuantityGreaterThan(Integer quantity);
}
