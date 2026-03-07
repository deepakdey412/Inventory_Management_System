package com.erp.InventoryManagementSystem.security;

import com.erp.InventoryManagementSystem.entity.User;
import com.erp.InventoryManagementSystem.exceptions.NotFoundException;
import com.erp.InventoryManagementSystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserEmail(username).orElseThrow(
        ()-> new NotFoundException("User not found with this user name  : "+ username));

        return AuthUser.builder()
                .user(user)
                .build();
    }

}
