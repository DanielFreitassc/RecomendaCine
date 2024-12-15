package com.danielfreitassc.backend.dtos.media;

import java.util.List;

import com.danielfreitassc.backend.models.media.MediaTypeEnum;

public record MediaResponseDto(
    Long id,
    String title,
    String coverImage,
    String synopsis,
    List<String> genre,
    List<String> whereToWatch,
    MediaTypeEnum mediaType
) {

}
