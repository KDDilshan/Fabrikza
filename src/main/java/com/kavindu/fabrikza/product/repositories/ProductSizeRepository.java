package com.kavindu.fabrikza.product.repositories;

import com.kavindu.fabrikza.product.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<Size,Integer> {
    Optional<Size> findByLabel(String label);
}

