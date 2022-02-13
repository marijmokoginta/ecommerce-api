package com.ecommerce.models.repositories;

import com.ecommerce.models.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserApp, Long> {
    UserApp findByEmail(String email);
    boolean existsByEmail(String email);
}
