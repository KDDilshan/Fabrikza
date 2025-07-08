package com.kavindu.fabrikza.product.repositories;

import com.kavindu.fabrikza.Authentication.models.AppUser;
import com.kavindu.fabrikza.product.models.Category;
import com.kavindu.fabrikza.product.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository  extends JpaRepository<Product, UUID> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategory(Category category);
    List<Product> findByManufacturerIgnoreCase(String manufacturer);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findAll(Sort sort);
    List<Product> findByCreatedBy(AppUser user);


    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> searchByName(@Param("query") String query);

    Optional<Product> findByNameAndCategory(String name, Category category);
}
