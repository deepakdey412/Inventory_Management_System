package com.erp.InventoryManagementSystem.controller;

import com.erp.InventoryManagementSystem.dtos.LoginDto;
import com.erp.InventoryManagementSystem.dtos.RegisterDto;
import com.erp.InventoryManagementSystem.dtos.ResponseDto;
import com.erp.InventoryManagementSystem.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ResponseDto>> getUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginDto loginDto)
    {
        userService.loginUser(loginDto);
        return ResponseEntity.ok(new ResponseDto());
    }
}
