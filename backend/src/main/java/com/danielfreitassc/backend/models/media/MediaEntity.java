package com.danielfreitassc.backend.models.media;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "media")
public class MediaEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    private String coverImage;
    
    @Column(columnDefinition="TEXT")
    private String synopsis;

    @ElementCollection
    @CollectionTable(name = "media_entity_genre", joinColumns = @JoinColumn(name = "media_id"))
    @Column(name = "genre")
    private List<String> genre;
    private List<String> whereToWatch; 
    private MediaTypeEnum mediaType;
}
