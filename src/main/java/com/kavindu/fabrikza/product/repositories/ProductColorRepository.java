package com.kavindu.fabrikza.product.repositories;

import com.kavindu.fabrikza.product.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository<Color,Integer> {
}
