package com.erp.InventoryManagementSystem.repository;

import com.erp.InventoryManagementSystem.entity.Category;
import com.erp.InventoryManagementSystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    
}
