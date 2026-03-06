package com.erp.InventoryManagementSystem.dtos;

import com.erp.InventoryManagementSystem.enums.TransactionType;
import com.erp.InventoryManagementSystem.enums.TransactionsStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionsDTO {
    private Long transactionId;

    private Integer totalProductQuantity;

    private BigDecimal totalProductPrice;

    private TransactionType transactionType;// PURCHASE / SALE / RETURN

    private TransactionsStatus transactionStatus;// PENDING,PROCESSING,COMPLETED,CANCELLED,

    private String description;

    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProductsDTO product;

    private UserDTO user;

    private SupplierDTO supplier;
}
