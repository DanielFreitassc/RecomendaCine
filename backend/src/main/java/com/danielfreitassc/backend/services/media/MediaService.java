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

import com.danielfreitassc.backend.dtos.media.MediaRequestDto;
import com.danielfreitassc.backend.dtos.media.MediaResponseDto;
import com.danielfreitassc.backend.mappers.media.MediaMapper;
import com.danielfreitassc.backend.models.media.MediaEntity;
import com.danielfreitassc.backend.models.user.UserEntity;
import com.danielfreitassc.backend.repositories.media.MediaRepository;
import com.danielfreitassc.backend.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private static Long lastMediaId = null;
    private final UserRepository userRepository;

    public MediaResponseDto create(MediaRequestDto mediaRequestDto) {
        return mediaMapper.toDto(mediaRepository.save(mediaMapper.toEntity(mediaRequestDto)));
    }

    public Page<MediaResponseDto> getPage(Pageable pageable) {
        Page<MediaEntity> allMedia = mediaRepository.findAll(pageable);
        return allMedia.map(mediaMapper::toDto);
    }

    public MediaResponseDto recommendation(UUID id) {
        Optional<UserEntity> userId = userRepository.findById(id);
        if(userId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado");
        UserEntity userEntity = userId.get();

        List<MediaEntity> allMedia = mediaRepository.findByGenreInAndMediaType(userEntity.getFavoriteGenre(), userEntity.getFavoriteMediaType());

        if (allMedia.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma mídia disponível");
        

        if (lastMediaId != null) allMedia.removeIf(media -> media.getId().equals(lastMediaId));
        

        if (allMedia.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Todas as mídias Já foram recomendadas");
        

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

        if (media == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma mídia foi encontrada!");
        
        lastMediaId = media.getId();

        return mediaMapper.toDto(media);
    }
}
