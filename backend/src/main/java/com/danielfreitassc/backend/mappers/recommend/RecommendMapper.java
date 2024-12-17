package com.danielfreitassc.backend.mappers.recommend;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.recommend.RecommendRequestDto;
import com.danielfreitassc.backend.dtos.recommend.RecommendResponseDto;
import com.danielfreitassc.backend.models.recommend.RecommendEntity;

@Mapper(componentModel="spring")
public interface RecommendMapper {
    RecommendResponseDto toDto(RecommendEntity recommendEntity);

    @Mapping(target="id",ignore=true)
    @Mapping(target="createdAt",ignore=true)
    @Mapping(target="user.id",source="userId")
    @Mapping(target="media.id",source="mediaId")
    RecommendEntity toEntity(RecommendRequestDto recommendRequestDto);
}
