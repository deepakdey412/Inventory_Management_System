package com.erp.InventoryManagementSystem.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequestDto{

    private String productId;
    private Integer productQuantity;
    private String supplierId;
    private String description;
    private String note;

}
