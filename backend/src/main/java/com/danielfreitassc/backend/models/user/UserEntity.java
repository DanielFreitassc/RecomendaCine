package com.danielfreitassc.backend.models.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.danielfreitassc.backend.models.media.MediaTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String name;
    private String username;
    private String email;
    @Column(columnDefinition="TEXT")
    private String image;

    private MediaTypeEnum favoriteMediaType;

    @ElementCollection
    private List<String> favoriteGenre;

    @CreationTimestamp
    private Timestamp createdAt;

    @Length(min = 10, max = 255, message = "A senha deve Conter no mínimo 10 caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$", message = "A senha deve conter pelo menos uma letra minúscula e uma letra maiúscula.")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    @Column(name = "login_attempts")
    private int loginAttempts = 0;

    @Column(name = "lockout_expiration")
    private LocalDateTime lockoutExpiration;

    public void lockAccount() {
        this.lockoutExpiration = LocalDateTime.now().plusMinutes(10);
    }

    public boolean isAccountLocked() {
        return lockoutExpiration != null && LocalDateTime.now().isBefore(lockoutExpiration);
    }

    public UserEntity(String name,String username, String email, String image, String password, MediaTypeEnum favoriteMediaType, List<String> favoriteGenre,UserRole role) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.image = image;
        this.password = password;
        this.favoriteMediaType = favoriteMediaType;
        this.favoriteGenre = favoriteGenre;
        this.role = (role != null) ? role : UserRole.USER;
    }

    public void incrementLoginAttempts() {
        this.loginAttempts++;
    }

    public void resetLoginAttempts() {
        this.loginAttempts = 0;
    }

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.role != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.getRole().toUpperCase()));
        }
        return authorities;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return !isAccountLocked();
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
