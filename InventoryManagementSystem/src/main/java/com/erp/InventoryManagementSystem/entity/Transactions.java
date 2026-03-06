package com.erp.InventoryManagementSystem.entity;

import com.erp.InventoryManagementSystem.enums.TransactionType;
import com.erp.InventoryManagementSystem.enums.TransactionsStatus;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction_table")
public class Transactions {

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
    private TransactionsStatus  transactionStatus;// PENDING,PROCESSING,COMPLETED,CANCELLED,

    private String description;
    private String notes;

    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
