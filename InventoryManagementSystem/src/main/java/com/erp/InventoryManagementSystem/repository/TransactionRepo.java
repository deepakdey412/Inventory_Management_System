package com.erp.InventoryManagementSystem.repository;

import com.erp.InventoryManagementSystem.entity.Category;
import com.erp.InventoryManagementSystem.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepo extends JpaRepository<Transactions, Long> , JpaSpecificationExecutor<Transactions> {

}

