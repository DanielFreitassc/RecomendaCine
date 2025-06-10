package com.danielfreitassc.backend.mappers.media;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.media.GenresResponseDto;
import com.danielfreitassc.backend.dtos.media.MediaRequestDto;
import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.models.media.MediaEntity;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaResponseDto toDto(MediaEntity mediaEntity);
    
    @Mapping(target="id",ignore=true)
    MediaEntity toEntity(MediaRequestDto mediaRequestDto);

    GenresResponseDto toGenres(MediaEntity mediaEntity);
}
