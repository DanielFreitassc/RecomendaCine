package com.danielfreitassc.backend.services.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.user.UserRequestDto;
import com.danielfreitassc.backend.dtos.user.UserResponseDto;
import com.danielfreitassc.backend.mappers.user.UserMapper;
import com.danielfreitassc.backend.models.user.UserEntity;
import com.danielfreitassc.backend.models.user.UserRole;
import com.danielfreitassc.backend.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public MessageResponseDto create(UserRequestDto userRequestDto) {
        checkDuplicateUsername(userRequestDto.email());

        String encryptedPassword =  new BCryptPasswordEncoder().encode(userRequestDto.password());
        UserEntity userEntity = userRepository.save(userMapper.toEntity(userRequestDto));

        userEntity.setRole(UserRole.USER);
        userEntity.setPassword(encryptedPassword);
        userRepository.save(userEntity);
        return new MessageResponseDto("Usuário cadastrado com sucesso!");
    }

    public Page<UserResponseDto> getAllUsers(Pageable pageable, String search) {
        return userRepository.findAllByRoleNot(UserRole.ADMIN ,pageable,search).map(userMapper::toDto);
    }

    public UserResponseDto getUserById(UUID id) {
        return userMapper.toDto(findUserOrThrow(id));
    }

    public UserResponseDto patchUser(UUID id,  UserRequestDto userRequestDto) {
   
        UserEntity userEntity = findUserOrThrow(id);
        
        if (userRequestDto.name() != null && !userRequestDto.name().isBlank()) {
            userEntity.setName(userRequestDto.name());
        }
        if (userRequestDto.image() != null && !userRequestDto.image().isBlank()) {
            userEntity.setImage(userRequestDto.image());
        }
        if(userRequestDto.email() != null && !userRequestDto.email().isBlank()) {
            userEntity.setEmail(userRequestDto.email());
        }
        
        if (userRequestDto.favoriteMediaType() != userEntity.getFavoriteMediaType()) {
            userEntity.setFavoriteMediaType(userRequestDto.favoriteMediaType());
        }
    
        if (userRequestDto.password() != null && !userRequestDto.password().isBlank()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userRequestDto.password());
            userEntity.setPassword(encryptedPassword);
        }
        
        if (!userRequestDto.favoriteGenre().isEmpty()) {
            userEntity.setFavoriteGenre(userRequestDto.favoriteGenre());
        }

        return userMapper.toDto(userRepository.save(userEntity));
    }


    public UserResponseDto delete(UUID id) {
        userRepository.delete(findUserOrThrow(id));
        return userMapper.toDto(findUserOrThrow(id));
    }

     // verifica se username já existe
     private void checkDuplicateUsername(String email) {
        if(userRepository.findByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuário já cadastrado na base de dados.");
        }
    }

    // Busca usuário ou retorna 404
    private UserEntity findUserOrThrow(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado");
        return user.get();
    }
}
