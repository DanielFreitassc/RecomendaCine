package com.danielfreitassc.backend.dtos.media;

import java.util.List;

import com.danielfreitassc.backend.models.media.MediaTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MediaRequestDto(
    @NotBlank(message="Por favor, insira o título para continuar.")
    String title,
    @NotBlank(message="Por favor, insira a capa do filme para continuar.")
    String coverImage,
    @NotBlank(message="Por favor, insira a sinopse do filme para continuar.")
    String synopsis,
    @NotNull(message="Por favor, insira o gênero do filme para continuar.")
    List<String> genre,
    @NotNull(message="Por favor, insira onde assisir o filme para continuar.")
    List<String> whereToWatch,
    @NotNull(message="Por favor, insira o tipo do filme para continuar.")
    MediaTypeEnum mediaType
) {
    
}
