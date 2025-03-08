package com.eliscioter.terra.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "registered_devices")
public class RegisteredDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Unique
    private String deviceToken;

    @Column(nullable = false)
    private Date createdAt;

    public RegisteredDeviceEntity() {
        this.createdAt = java.sql.Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Unique String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(@Unique String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
