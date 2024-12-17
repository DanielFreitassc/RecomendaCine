package com.danielfreitassc.backend.models.recommend;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.danielfreitassc.backend.models.media.MediaEntity;
import com.danielfreitassc.backend.models.user.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="recommend_history")
public class RecommendEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="media_id")
    private MediaEntity media;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @CreationTimestamp
    private Timestamp createdAt;
}
