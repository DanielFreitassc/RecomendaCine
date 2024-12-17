package com.danielfreitassc.backend.mappers.disliked;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.disliked.DislikedRequestDto;
import com.danielfreitassc.backend.dtos.disliked.DislikedResponseDto;
import com.danielfreitassc.backend.models.disliked.DislikedEntity;

@Mapper(componentModel = "spring")
public interface DislikedMapper {
    DislikedResponseDto toDto(DislikedEntity dislikeEntity);
    @Mapping(target="id",ignore=true)
    @Mapping(target="createdAt",ignore=true)
    @Mapping(target="media.id",source="mediaId")
    @Mapping(target="user.id",source="userId")
    DislikedEntity toEntity(DislikedRequestDto dislikedRequestDto);
}
