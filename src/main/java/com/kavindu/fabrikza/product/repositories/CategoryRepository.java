package com.kavindu.fabrikza.product.repositories;

import com.kavindu.fabrikza.product.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByName(String name);
}
