package com.erp.InventoryManagementSystem.repository;

import com.erp.InventoryManagementSystem.entity.Category;
import com.erp.InventoryManagementSystem.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}

