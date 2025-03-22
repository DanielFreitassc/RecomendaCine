package com.danielfreitassc.backend.dtos.user;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.danielfreitassc.backend.configurations.OnCreate;
import com.danielfreitassc.backend.models.media.MediaTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record  UserRequestDto(
    @NotBlank(groups=OnCreate.class, message="O campo nome não pode estar em branco.") 
    String name,

    @NotBlank(groups=OnCreate.class, message="Imagem não pode estar vazia.") 
    String image,

    @NotBlank(groups=OnCreate.class, message="O campo email não pode estar em branco.") 
    String email,

    @Length(groups = OnCreate.class, min = 6,message = "A senha deve Conter no mínimo 6 caracteres.")
    @Pattern(groups = OnCreate.class, regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$", message = "A senha deve conter pelo menos uma letra minúscula e uma letra maiúscula.")
    @NotBlank(groups=OnCreate.class, message = "O campo senha não pode estar em branco.") 
    String password,

    @NotNull(groups=OnCreate.class, message = "O campo senha não pode estar em branco.") 
    MediaTypeEnum favoriteMediaType,

    @NotNull(groups=OnCreate.class, message = "O campo senha não pode estar em branco.") 
    List<String> favoriteGenre
) {
    
}
