package com.danielfreitassc.backend.services.user;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.user.UserRequestDto;
import com.danielfreitassc.backend.mappers.user.UserMapper;
import com.danielfreitassc.backend.models.media.MediaTypeEnum;
import com.danielfreitassc.backend.models.user.UserEntity;
import com.danielfreitassc.backend.models.user.UserRole;
import com.danielfreitassc.backend.repositories.user.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer {
    private final UserRepository userRepository;
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @PostConstruct
    public void init() {

        if(userRepository.findByEmail(adminUsername) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuário já existe");
        } else {
            String encryptedPassword =  new BCryptPasswordEncoder().encode(adminPassword);
            UserEntity userEntity = new UserEntity("ADMIN","admin@admin.com","image",encryptedPassword,MediaTypeEnum.MOVIE, Arrays.asList("Ação"),UserRole.ADMIN);
            userRepository.save(userEntity);
        }
    }
}