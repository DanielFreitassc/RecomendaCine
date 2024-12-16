package com.danielfreitassc.backend.mappers.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.user.AuthenticationDTO;
import com.danielfreitassc.backend.models.user.UserEntity;



@Mapper(componentModel = "spring")
public interface  AuhtenticationMapper {
    
    AuthenticationDTO toDto(UserEntity user);

    @Mapping(target="id", ignore=true)
    @Mapping(target="name", ignore=true)
    @Mapping(target="image", ignore=true)
    @Mapping(target="role", ignore=true)
    @Mapping(target="email", ignore=true)
    @Mapping(target="favoriteGenre", ignore=true)
    @Mapping(target="createdAt", ignore=true)
    @Mapping(target="loginAttempts", ignore=true)
    @Mapping(target="lockoutExpiration",ignore=true)
    @Mapping(target="authorities",ignore=true)
    UserEntity toEntity(AuthenticationDTO authenticationDTO); 
}