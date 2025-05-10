package com.eliscioter.terra.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "registered_devices")
public class RegisteredDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String deviceToken;

    @Column(unique = true, nullable = false)
    private String correlationToken;

    @Column(nullable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "registeredDevice")
    private List<UserLottoDetailsEntity> userLottoDetailEntities;

    public RegisteredDeviceEntity() {
        this.createdAt = java.sql.Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getCorrelationToken() {
        return correlationToken;
    }

    public void setCorrelationToken(String correlationToken) {
        this.correlationToken = correlationToken;
    }

    public List<UserLottoDetailsEntity> getUserLottoDetails() {
        return userLottoDetailEntities;
    }

    public void setUserLottoDetails(List<UserLottoDetailsEntity> userLottoDetailEntities) {
        this.userLottoDetailEntities = userLottoDetailEntities;
    }
}
