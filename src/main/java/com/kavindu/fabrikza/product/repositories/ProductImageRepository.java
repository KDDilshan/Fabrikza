package com.kavindu.fabrikza.product.repositories;

import com.kavindu.fabrikza.product.models.Color;
import com.kavindu.fabrikza.product.models.Product;
import com.kavindu.fabrikza.product.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
    List<ProductImage> findByProduct(Product product);
    List<ProductImage> findByColor(Color color);
    List<ProductImage> findByProductAndColor(Product product, Color color);

    Optional<ProductImage> findFirstByProductIdAndColorId(UUID id, int id1);
}
