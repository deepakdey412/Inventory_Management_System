package com.erp.InventoryManagementSystem.services.impl;

import com.erp.InventoryManagementSystem.dtos.LoginDto;
import com.erp.InventoryManagementSystem.dtos.RegisterDto;
import com.erp.InventoryManagementSystem.dtos.ResponseDto;
import com.erp.InventoryManagementSystem.dtos.UserDTO;
import com.erp.InventoryManagementSystem.entity.User;
import com.erp.InventoryManagementSystem.enums.UserRole;
import com.erp.InventoryManagementSystem.exceptions.NotFoundException;
import com.erp.InventoryManagementSystem.repository.UserRepo;
import com.erp.InventoryManagementSystem.security.JwtUtil;
import com.erp.InventoryManagementSystem.services.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseDto registerUser(RegisterDto registerDto) {

        UserRole userRole = UserRole.MANAGER;

        if (registerDto.getRole() != null) {
            userRole = registerDto.getRole();
        }

        User userToSave = User.builder()
                .userName(registerDto.getName())
                .userEmail(registerDto.getEmail())
                .userPassword(passwordEncoder.encode(registerDto.getPassword()))
                .userPhoneNumber(registerDto.getPhoneNumber())
                .userRole(userRole)
                .build();

        userRepo.save(userToSave);

        return ResponseDto.builder()
                .status(200)
                .message("User registered successfully")
                .build();
    }

    @Override
    public ResponseDto loginUser(LoginDto loginDto) {

        User user = userRepo.findByUserEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getUserPassword())) {
            throw new RuntimeException("Password does not match");
        }

        String token = jwtUtil.generateToken(user.getUserEmail());

        return ResponseDto.builder()
                .status(200)
                .message("Login successful")
                .token(token)
                .role(user.getUserRole())
                .build();
    }

    @Override
    public ResponseDto getAllUsers() {

        List<User> users = userRepo.findAll();

        List<UserDTO> userDTOList = users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();

        return ResponseDto.builder()
                .status(200)
                .message("Users fetched successfully")
                .users(userDTOList)
                .build();
    }

    @Override
    public ResponseDto getUserById(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return ResponseDto.builder()
                .status(200)
                .message("success")
                .userDto(userDTO)
                .build();
    }

    @Override
    public User getCurrentLogedInUser() {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String email = authentication.getName();

            User user = userRepo.findByUserEmail(email).orElseThrow(()-> new NotFoundException("User Not Found"));

            user.setUserTransactions(null);

            return user;
    }

    @Override
    public ResponseDto updateUser(Long id, UserDTO userDTO) {

        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userDTO.getUserEmail() != null)
            existingUser.setUserEmail(userDTO.getUserEmail());

        if (userDTO.getUserPhoneNumber() != null)
            existingUser.setUserPhoneNumber(userDTO.getUserPhoneNumber());

        if (userDTO.getUserName() != null)
            existingUser.setUserName(userDTO.getUserName());

        if (userDTO.getUserRole() != null)
            existingUser.setUserRole(userDTO.getUserRole());

        if (userDTO.getUserPassword() != null && !userDTO.getUserPassword().isEmpty()) {
            existingUser.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        }

        userRepo.save(existingUser);

        return ResponseDto.builder()
                .status(200)
                .message("User updated successfully")
                .build();
    }

    @Override
    public ResponseDto deleteUser(Long id) {

        userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepo.deleteById(id);

        return ResponseDto.builder()
                .status(200)
                .message("User deleted successfully")
                .build();
    }

    @Override
    public ResponseDto getUserTransaction(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return ResponseDto.builder()
                .status(200)
                .message("success")
                .userDto(userDTO)
                .build();
    }

}