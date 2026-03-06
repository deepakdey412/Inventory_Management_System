package com.erp.InventoryManagementSystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsDTO {

    private Long productId;
    private Long categoryId;
    private Long supplierId;

    private String productName;

    private String productSku;

    private String productDescription;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private LocalDateTime expirationDate;

    private String imageURL;

    private LocalDateTime createdAt;
}