package com.danielfreitassc.backend.mappers.favorite;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.favorite.FavoriteRequestDto;
import com.danielfreitassc.backend.dtos.favorite.FavoriteResponseDto;
import com.danielfreitassc.backend.models.favorite.FavoriteEntity;

@Mapper(componentModel="spring")
public interface FavoriteMapper {
    FavoriteResponseDto toDto(FavoriteEntity favoriteEntity); 

    @Mapping(target="id", ignore=true)
    @Mapping(target="createdAt", ignore=true)
    @Mapping(target="media.id", source="mediaId")
    @Mapping(target="user.id", source="userId")
    FavoriteEntity toEntity(FavoriteRequestDto favoriteRequestDto);
}
