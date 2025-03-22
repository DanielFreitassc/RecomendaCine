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

import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.disliked.DislikedRequestDto;
import com.danielfreitassc.backend.dtos.disliked.DislikedResponseDto;
import com.danielfreitassc.backend.dtos.favorite.FavoriteRequestDto;
import com.danielfreitassc.backend.dtos.favorite.FavoriteResponseDto;
import com.danielfreitassc.backend.dtos.media.MediaRequestDto;
import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.dtos.recommend.RecommendRequestDto;
import com.danielfreitassc.backend.dtos.recommend.RecommendResponseDto;
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
        // Antes de recomendar verfica se usuário existe
        UserEntity userEntity = findUserOrThrow(id);
    
        List<MediaEntity> allMedia = mediaRepository.findByGenreInAndMediaType(
            userEntity.getFavoriteGenre(), 
            userEntity.getFavoriteMediaType()
        );
    
        if (allMedia.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma mídia disponível");
    
        List<Long> dislikedMediaIds = dislikedRepository.findByUser_Id(id).stream()
            .map(disliked -> disliked.getMedia().getId())
            .toList();
    
        allMedia.removeIf(media -> 
            dislikedMediaIds.contains(media.getId()) || 
            (lastMediaId != null && media.getId().equals(lastMediaId)) 
        );
    
        if (allMedia.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma mídia disponível para recomendação.");
    
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
    
        if (existingRecommendation.isEmpty()) {
            RecommendRequestDto recommendRequestDto = new RecommendRequestDto(media.getId(), userEntity.getId());
            RecommendEntity recommendEntity = recommendMapper.toEntity(recommendRequestDto);
            recommendRepository.save(recommendEntity);
        }
    
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

    public MessageResponseDto removeFavorite(FavoriteRequestDto favoriteRequestDto) {
        Optional<FavoriteEntity> favorite = favoriteRepository.findByUser_IdAndMedia_Id(
            favoriteRequestDto.userId(),
            favoriteRequestDto.mediaId()
        );
    
        if (favorite.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mídia já foi removida dos favoritos!");
        }
    
        favoriteRepository.delete(favorite.get());
    
        return new MessageResponseDto("Mídia removida com sucesso dos favoritos!");
    }
    

    // Retorna lista de favoritos de um usuário
    public List<FavoriteResponseDto> getAllFavoriteMedia(UUID id) {
        return findFavorieOrThrow(id).stream().map(favoriteMapper::toDto).toList();
    }

    // Rotorna lista de filmes já recomendados
    public List<RecommendResponseDto> getRecommend(UUID id) {
        return  findRecommendOrThrow(id).stream().map(recommendMapper::toDto).toList();
    }

    // Salva filme em lista de não recomendados
    public DislikedResponseDto saveDisliked(DislikedRequestDto dislikedRequestDto) {
        // Verifica se já esta na lista de não recomendados
        ensureMediaNotDisliked(dislikedRequestDto);
        
        return dislikedMapper.toDto(dislikedRepository.save(dislikedMapper.toEntity(dislikedRequestDto)));
    }

    // Remove mídia da lista de não recomendados
    public MessageResponseDto removeDisliked(DislikedRequestDto dislikedRequestDto) {
    
        dislikedRepository.delete(verifyDislikedMedia(dislikedRequestDto));
    
        return new MessageResponseDto("A mídia foi removida da lista de não recomendados.");
    }

    // Retorna lista de não recomendados
    public List<DislikedResponseDto> getDisliked(UUID id) {
        return findAllDisliked(id).stream().map(dislikedMapper::toDto).toList();
    }


    // Função que verifica se mídia está presente na lista de não recomendados
    private DislikedEntity verifyDislikedMedia(DislikedRequestDto dislikedRequestDto) {
        // Chama função que verifica se usuário existe
        findUserOrThrow(dislikedRequestDto.userId());

        Optional<DislikedEntity> disliked = dislikedRepository.findByUser_IdAndMedia_Id(
            dislikedRequestDto.userId(),
            dislikedRequestDto.mediaId()
        );
    
        if (disliked.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A mídia não foi adicionada à lista de exclusão.");
        return disliked.get();
    }

    // Função para verificar se filme já está na lista de filmes não recomendados
    private boolean ensureMediaNotDisliked(DislikedRequestDto dislikedRequestDto) {
        // Chama função que verifica se usuário existe
        findUserOrThrow(dislikedRequestDto.userId());

        boolean exists = dislikedRepository.existsByUser_IdAndMedia_Id( 
            dislikedRequestDto.userId(),
            dislikedRequestDto.mediaId()
        );

        if (exists) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A mídia já foi adicionada à lista de exclusão.");
        return exists;
    }

    // Função que faz uma busca na lista de recomendados relacionado ao id do usuário.
    private List<RecommendEntity> findRecommendOrThrow(UUID id) {
        // Chama função que verifica se usuário existe
        findUserOrThrow(id);

        List<RecommendEntity> recommend = recommendRepository.findByUser_Id(id);
        if(recommend.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lista de recomendados está vazia");
        return recommend;
    }

    // Função que faz uma busca na lista de favoritos relacionado ao id do usuário.
    private List<FavoriteEntity> findFavorieOrThrow(UUID id) {
        // Chama função que verifica se usuário existe
        findUserOrThrow(id);

        List<FavoriteEntity> favorites = favoriteRepository.findByUser_Id(id);
        if(favorites.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lista de filmes favoritos está vaiza");
        return favorites;
    }

    // Função que faz uma busca na lista de dislike relacionado ao id do usuário.
    private List<DislikedEntity> findAllDisliked(UUID id) {
        // Chama função que verifica se usuário existe
        findUserOrThrow(id);

        List<DislikedEntity> disliked = dislikedRepository.findByUser_Id(id);
        if(disliked.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lista de filmes não recomendados está vazia");
        return disliked;
    }

    // Função pra verficar se usuário existe, se existir retorna o mesmo.
    private UserEntity findUserOrThrow(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum usuário encontrado");
        return user.get();
    }
}
