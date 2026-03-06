package com.erp.InventoryManagementSystem.dtos;

import com.erp.InventoryManagementSystem.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class UserDTO {

    private Long userId;

    private String userName;

    private String userEmail;

    @JsonIgnore
    private String userPassword;

    private String userPhoneNumber;

    private UserRole userRole;

    private List<TransactionsDTO> userTransactions;

    private LocalDateTime createdAt;
}