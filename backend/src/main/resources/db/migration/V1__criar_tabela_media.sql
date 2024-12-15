-- Criando a tabela 'MEDIA'
CREATE TABLE IF NOT EXISTS media (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    cover_image CLOB,
    media_type TINYINT,
    synopsis VARCHAR,
    where_to_watch VARCHAR(255),
    CONSTRAINT media_unique_genre UNIQUE (id)  
);

CREATE TABLE IF NOT EXISTS media_entity_genre (
    media_id BIGINT NOT NULL,
    genre VARCHAR(255) NOT NULL,
    CONSTRAINT fk_media FOREIGN KEY (media_id) REFERENCES media(id) ON DELETE CASCADE,
    CONSTRAINT media_entity_genre_unique UNIQUE (media_id, genre)
);

CREATE INDEX fk_media_id_idx ON media_entity_genre (media_id);
