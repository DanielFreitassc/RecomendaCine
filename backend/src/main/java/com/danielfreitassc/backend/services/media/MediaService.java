package com.danielfreitassc.backend.services.media;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.disliked.DislikedRequestDto;
import com.danielfreitassc.backend.dtos.disliked.DislikedResponseDto;
import com.danielfreitassc.backend.dtos.favorite.FavoriteRequestDto;
import com.danielfreitassc.backend.dtos.favorite.FavoriteResponseDto;
import com.danielfreitassc.backend.dtos.media.MediaRequestDto;
import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.dtos.recommend.RecommendRequestDto;
import com.danielfreitassc.backend.dtos.recommend.RecommendResponseDto;
import com.danielfreitassc.backend.dtos.user.ResponseMessageDTO;
import com.danielfreitassc.backend.mappers.disliked.DislikedMapper;
import com.danielfreitassc.backend.mappers.favorite.FavoriteMapper;
import com.danielfreitassc.backend.mappers.media.MediaMapper;
import com.danielfreitassc.backend.mappers.recommend.RecommendMapper;
import com.danielfreitassc.backend.models.disliked.DislikedEntity;
import com.danielfreitassc.backend.models.favorite.FavoriteEntity;
import com.danielfreitassc.backend.models.media.MediaEntity;
import com.danielfreitassc.backend.models.recommend.RecommendEntity;
import com.danielfreitassc.backend.models.user.UserEntity;
import com.danielfreitassc.backend.repositories.disliked.DislikedRepository;
import com.danielfreitassc.backend.repositories.favorite.FavoriteRepository;
import com.danielfreitassc.backend.repositories.media.MediaRepository;
import com.danielfreitassc.backend.repositories.recommend.RecommendRepository;
import com.danielfreitassc.backend.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private static Long lastMediaId = null;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository; 
    private final FavoriteMapper favoriteMapper;
    private final RecommendMapper recommendMapper;
    private final RecommendRepository recommendRepository;
    private final DislikedMapper dislikedMapper;
    private final DislikedRepository dislikedRepository;

    public MediaResponseDto create(MediaRequestDto mediaRequestDto) {
        return mediaMapper.toDto(mediaRepository.save(mediaMapper.toEntity(mediaRequestDto)));
    }

    public Page<MediaResponseDto> getPage(Pageable pageable) {
        Page<MediaEntity> allMedia = mediaRepository.findAll(pageable);
        return allMedia.map(mediaMapper::toDto);
    }

    public MediaResponseDto recommendation(UUID id) {
        Optional<UserEntity> userId = userRepository.findById(id);
        if (userId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        UserEntity userEntity = userId.get();
    
        List<MediaEntity> allMedia = mediaRepository.findByGenreInAndMediaType(
            userEntity.getFavoriteGenre(), 
            userEntity.getFavoriteMediaType()
        );
    
        if (allMedia.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma mídia disponível");
    
        List<Long> dislikedMediaIds = dislikedRepository.findByUser_Id(id).stream()
            .map(disliked -> disliked.getMedia().getId())
            .toList();
    
        allMedia.removeIf(media -> dislikedMediaIds.contains(media.getId()) || 
                                    (lastMediaId != null && media.getId().equals(lastMediaId)));
    
        if (allMedia.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todas as mídias já foram recomendadas ou estão na lista de exclusão.");
    
        Random random = new Random();
        MediaEntity media = null;
        int maxAttempts = 10;
    
        for (int attempts = 0; attempts < maxAttempts; attempts++) {
            int randomIndex = random.nextInt(allMedia.size());
            media = allMedia.get(randomIndex);
    
            if (media != null) {
                break;
            }
        }
    
        if (media == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma mídia foi encontrada!");
    
        Optional<RecommendEntity> existingRecommendation = recommendRepository.findByUserAndMedia(userEntity, media);
        if (existingRecommendation.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta recomendação já foi feita para o usuário");
        }
    
        RecommendRequestDto recommendRequestDto = new RecommendRequestDto(media.getId(), userEntity.getId());
        RecommendEntity recommendEntity = recommendMapper.toEntity(recommendRequestDto);
        recommendRepository.save(recommendEntity);
    
        lastMediaId = media.getId();
    
        return mediaMapper.toDto(media);
    }
    

    
    public FavoriteResponseDto saveFavorite(FavoriteRequestDto favoriteRequestDto) {
        boolean exists = favoriteRepository.existsByUser_IdAndMedia_Id(
            favoriteRequestDto.userId(),
            favoriteRequestDto.mediaId()
        );

        if (exists) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mídia já está nos favoritos");
        
        return favoriteMapper.toDto(favoriteRepository.save(favoriteMapper.toEntity(favoriteRequestDto)));
    }

    public ResponseMessageDTO removeFavorite(FavoriteRequestDto favoriteRequestDto) {
        Optional<FavoriteEntity> favorite = favoriteRepository.findByUser_IdAndMedia_Id(
            favoriteRequestDto.userId(),
            favoriteRequestDto.mediaId()
        );
    
        if (favorite.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mídia já foi removida dos favoritos!");
        }
    
        favoriteRepository.delete(favorite.get());
    
        return new ResponseMessageDTO("Mídia removida com sucesso dos favoritos!");
    }
    
    public List<FavoriteResponseDto> getAllFavoriteMedia(UUID id) {
        List<FavoriteEntity> favorite = favoriteRepository.findByUser_Id(id);
        if(favorite.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum usuário encontrado.");
        return favorite.stream().map(favoriteMapper::toDto).toList();
    }

    public List<RecommendResponseDto> getRecommend(UUID id) {
        List<RecommendEntity> recommend = recommendRepository.findByUser_Id(id);
        if(recommend.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum usuário encontrado.");
        return recommend.stream().map(recommendMapper::toDto).toList();
    }
    
    public DislikedResponseDto saveDisliked(DislikedRequestDto dislikedRequestDto) {
        boolean exists = dislikedRepository.existsByUser_IdAndMedia_Id(
            dislikedRequestDto.userId(),
            dislikedRequestDto.mediaId()
        );

        if (exists) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A mídia já foi adicionada à lista de exclusão.");
        
        return dislikedMapper.toDto(dislikedRepository.save(dislikedMapper.toEntity(dislikedRequestDto)));
    }

    public ResponseMessageDTO removeDisliked(DislikedRequestDto dislikedRequestDto) {
        Optional<DislikedEntity> disliked = dislikedRepository.findByUser_IdAndMedia_Id(
            dislikedRequestDto.userId(),
            dislikedRequestDto.mediaId()
        );
    
        if (disliked.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A mídia não foi adicionada à lista de exclusão.");
        }
    
        dislikedRepository.delete(disliked.get());
    
        return new ResponseMessageDTO("A mídia foi removida da lista de exclusão.");
    }

    public List<DislikedResponseDto> getDisliked(UUID id) {
        List<DislikedEntity> disliked = dislikedRepository.findByUser_Id(id);
        if(disliked.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum usuário encontrado.");
        return disliked.stream().map(dislikedMapper::toDto).toList();
    }
}
