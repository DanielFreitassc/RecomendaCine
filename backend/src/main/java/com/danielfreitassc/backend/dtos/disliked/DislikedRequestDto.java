package com.danielfreitassc.backend.dtos.disliked;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record DislikedRequestDto(
    @NotNull(message="Necessário o id da mídia!")
    Long mediaId,
    @NotNull(message="Necessário o id do usuário!")
    UUID userId
) {
    
}
