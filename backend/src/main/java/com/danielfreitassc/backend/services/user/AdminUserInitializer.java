package com.danielfreitassc.backend.services.user;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.danielfreitassc.backend.dtos.user.UserRequestDto;
import com.danielfreitassc.backend.mappers.user.UserMapper;
import com.danielfreitassc.backend.models.user.UserRole;
import com.danielfreitassc.backend.repositories.user.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer {
    private  final UserMapper userMapper;
    private final UserRepository userRepository;
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @PostConstruct
    public void init() {

        if(userRepository.findByUsername(adminUsername) != null) {
            System.out.println("Erro");
        } else {
            String encryptedPassword =  new BCryptPasswordEncoder().encode(adminPassword);
            UserRequestDto adminUserDTO = new UserRequestDto("Admin", "admin", "foto", "admin@admin.com", encryptedPassword, Arrays.asList("Ação"),  UserRole.ADMIN);
            
            userRepository.save(userMapper.toEntity(adminUserDTO));
        }
    }
}