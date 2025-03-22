package com.danielfreitassc.backend.repositories.user;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.danielfreitassc.backend.models.user.UserEntity;
import com.danielfreitassc.backend.models.user.UserRole;


public interface UserRepository extends JpaRepository<UserEntity, UUID>{

    UserDetails findByEmail(String email);
    
    @Query("SELECT u FROM UserEntity u WHERE UPPER(u.name) LIKE CONCAT('%', UPPER(:search), '%') AND u.role <> :role ORDER BY u.createdAt DESC")
    Page<UserEntity> findAllByRoleNot(UserRole role, Pageable pageable, String search);
    
}
