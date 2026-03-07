package com.erp.InventoryManagementSystem.repository;

import com.erp.InventoryManagementSystem.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepo extends JpaRepository<Products, Long> {
    List<Products> findByProductNameContainingOrProductDescriptionContaining(String name , String description);
}
