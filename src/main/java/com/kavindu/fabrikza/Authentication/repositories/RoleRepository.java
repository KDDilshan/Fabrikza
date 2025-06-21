package com.kavindu.fabrikza.Authentication.repositories;

import com.kavindu.fabrikza.Authentication.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(String name);
}
