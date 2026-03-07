package com.erp.InventoryManagementSystem.controller;

import com.erp.InventoryManagementSystem.dtos.LoginDto;
import com.erp.InventoryManagementSystem.dtos.RegisterDto;
import com.erp.InventoryManagementSystem.dtos.ResponseDto;
import com.erp.InventoryManagementSystem.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid RegisterDto registerDto) {

        ResponseDto response = userService.registerUser(registerDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginDto loginDto) {

        ResponseDto response = userService.loginUser(loginDto);

        return ResponseEntity.ok(response);
    }
}