package com.danielfreitassc.backend.dtos.user;

import java.util.List;

import com.danielfreitassc.backend.configurations.OnCreate;
import com.danielfreitassc.backend.models.user.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record  UserRequestDto(
    @NotBlank(groups=OnCreate.class, message="O campo nome não pode estar em branco.") String name,
    @NotBlank(groups=OnCreate.class, message = "O campo username não pode estar em branco.") String username,
    @NotBlank(groups=OnCreate.class, message="Imagem não pode estar vazia.") String image,
    @NotBlank(groups=OnCreate.class, message="O campo email não pode estar em branco.") String email,
    @NotBlank(groups=OnCreate.class, message = "O campo senha não pode estar em branco.") String password,
    @NotNull(groups=OnCreate.class, message = "O campo senha não pode estar em branco.") List<String> favoriteGenre,
    @NotNull(groups=OnCreate.class, message = "Regra de usário não pode ser um campo nulo.") UserRole role
) {
    
}
