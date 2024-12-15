package com.danielfreitassc.backend.models.media;

public enum MediaTypeEnum {
    MOVIE("Filme"),
    SERIES("Série"),
    ANIMATION("Animação"),
    DORAMA("Dorama"),
    ANIME("Anime");

    private final String description;

    MediaTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
