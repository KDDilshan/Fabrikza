package com.kavindu.fabrikza.product.repositories;

import com.kavindu.fabrikza.product.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductColorRepository extends JpaRepository<Color,Integer> {
    Optional<Color> findByNameIgnoreCase(String name);
    Optional<Color> findByHexCode(String hexCode);
    Optional<Color> findByNameIgnoreCaseAndHexCode(String name, String hexCode);
}
