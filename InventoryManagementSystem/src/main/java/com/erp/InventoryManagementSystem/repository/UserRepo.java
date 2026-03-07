package com.erp.InventoryManagementSystem.repository;

import com.erp.InventoryManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String email);
}
