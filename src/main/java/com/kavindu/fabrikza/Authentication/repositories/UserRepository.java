package com.kavindu.fabrikza.Authentication.repositories;

import com.kavindu.fabrikza.Authentication.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);

    Optional<AppUser>  findById(Integer userID);

    Optional<AppUser> findUserByEmail(String userEmail);

    Optional<AppUser> findByUsername(String username);
}
