package com.danielfreitassc.backend.dtos.user;

import java.util.List;
import java.util.UUID;

import com.danielfreitassc.backend.models.user.UserRole;

public record  UserResponseDto(
    UUID id,
    String name,
    String username,
    String image,
    String email,
    String password,
    List<String> favoriteGenre,
    UserRole role
) {
    
}
