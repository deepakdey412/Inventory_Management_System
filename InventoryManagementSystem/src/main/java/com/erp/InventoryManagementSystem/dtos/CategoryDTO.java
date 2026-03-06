package com.erp.InventoryManagementSystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private Long categoryId;

    @NotBlank(message = "Category name is required")
    private String categoryName;

    @NotBlank(message = "Category description is required")
    private String categoryDescription;

    private List<ProductsDTO> productsList;
}