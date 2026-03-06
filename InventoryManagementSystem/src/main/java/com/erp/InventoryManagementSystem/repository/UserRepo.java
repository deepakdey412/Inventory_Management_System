package com.erp.InventoryManagementSystem.repository;

import com.erp.InventoryManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
