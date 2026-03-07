package com.erp.InventoryManagementSystem.services;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.erp.InventoryManagementSystem.dtos.LoginDto;
import com.erp.InventoryManagementSystem.dtos.RegisterDto;
import com.erp.InventoryManagementSystem.dtos.ResponseDto;
import com.erp.InventoryManagementSystem.dtos.UserDTO;
import com.erp.InventoryManagementSystem.entity.User;

import java.util.List;

public interface IUserService {

    ResponseDto registerUser(RegisterDto registerDto);
    ResponseDto loginUser(LoginDto loginDto);
    ResponseDto getAllUsers();
    ResponseDto getUserById(Long id);
    User getCurrentLogedInUser();
    ResponseDto updateUser(Long id, UserDTO userDTO);
    ResponseDto deleteUser(Long id);
    ResponseDto getUserTransaction(Long id);
}
