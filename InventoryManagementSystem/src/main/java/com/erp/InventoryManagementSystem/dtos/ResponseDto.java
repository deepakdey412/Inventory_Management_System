package com.erp.InventoryManagementSystem.dtos;


import com.erp.InventoryManagementSystem.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseDto {

    //Generic
    private int status;
    private String message;

    //For log-in
    private String token;
    private UserRole role;
    private String expirationTime;

    //For pagination
    private Integer totalPages;
    private Long totalElements;

    //Data output optional
    private UserDTO userDto;
    private List<UserDTO> users;


}
