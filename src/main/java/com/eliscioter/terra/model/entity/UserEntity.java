package com.eliscioter.terra.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public final class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
