package com.erp.InventoryManagementSystem.dtos;

import com.erp.InventoryManagementSystem.enums.TransactionType;
import com.erp.InventoryManagementSystem.enums.TransactionsStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "total_product_quantity", nullable = false)
    private Integer totalProductQuantity;

    @Column(name = "total_product_price", nullable = false)
    private BigDecimal totalProductPrice;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;// PURCHASE / SALE / RETURN

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionsStatus transactionStatus;// PENDING,PROCESSING,COMPLETED,CANCELLED,

    private String description;
    private String notes;

    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductsDTO product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDTO user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private SupplierDTO supplier;
}
