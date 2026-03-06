package com.erp.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_table")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_sku", nullable = false)
    private String productSku;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_quantity")
    @Min(value = 0 ,message = "product quantity must be greater than 0")
    private Integer productQuantity;

    private LocalDateTime expirationDate;
    private String imageURL;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

//    @OneToOne(mappedBy = "product")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}