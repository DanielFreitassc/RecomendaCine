package com.danielfreitassc.backend.controllers.media;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.disliked.DislikedRequestDto;
import com.danielfreitassc.backend.dtos.disliked.DislikedResponseDto;
import com.danielfreitassc.backend.dtos.favorite.FavoriteRequestDto;
import com.danielfreitassc.backend.dtos.favorite.FavoriteResponseDto;
import com.danielfreitassc.backend.dtos.media.MediaRequestDto;
import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.dtos.recommend.RecommendResponseDto;
import com.danielfreitassc.backend.services.media.MediaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/media")
public class MediaController {
    private final MediaService mediaService;

    @PostMapping
    public MediaResponseDto saveMedia(@RequestBody @Valid MediaRequestDto mediaRequestDto) {
        return mediaService.create(mediaRequestDto);
    }

    @GetMapping
    public Page<MediaResponseDto> getMediaPage(Pageable pageable) {
        return mediaService.getPage(pageable); 
    } 

    @GetMapping("/recommendation/{id}")
    public MediaResponseDto recommendation(@PathVariable UUID id) {
        return mediaService.recommendation(id);
    }

    @PostMapping("/favorite")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto saveFavorite(@RequestBody @Valid FavoriteRequestDto favoriteRequestDto) {
        return mediaService.saveFavorite(favoriteRequestDto);
    }

    @DeleteMapping("/favorite")
    public MessageResponseDto removeFavorite(@RequestBody @Valid FavoriteRequestDto favoriteRequestDto) {
        return mediaService.removeFavorite(favoriteRequestDto);
    }
    
    @GetMapping("/favorite/{id}")
    public List<FavoriteResponseDto> getAllFavoriteMedia(@PathVariable UUID id) {
        return mediaService.getAllFavoriteMedia(id);
    }

    @GetMapping("/recommend/{id}")
    public List<RecommendResponseDto> getRecommend(@PathVariable UUID id) {
        return mediaService.getRecommend(id);
    }

    @PostMapping("/disliked")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto saveDisliked(@RequestBody @Valid DislikedRequestDto dislikedRequestDto) {
        return mediaService.saveDisliked(dislikedRequestDto);
    }

    @DeleteMapping("/disliked")
    public MessageResponseDto removeDisliked(@RequestBody @Valid DislikedRequestDto dislikedRequestDto) {
        return  mediaService.removeDisliked(dislikedRequestDto);
    }

    @GetMapping("/disliked/{id}")
    public List<DislikedResponseDto> getDisliked(@PathVariable UUID id) {
        return mediaService.getDisliked(id);
    }
}
