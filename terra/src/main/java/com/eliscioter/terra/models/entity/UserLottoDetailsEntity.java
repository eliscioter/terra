package com.eliscioter.terra.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "user_lotto_details")
public class UserLottoDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String combination;

    private String category;

    private String drawDate;

    @ManyToOne
    @JoinColumn(name = "correlation_token", referencedColumnName = "correlationToken")
    private RegisteredDeviceEntity registeredDevice;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public RegisteredDeviceEntity getRegisteredDevice() {
        return registeredDevice;
    }

    public void setRegisteredDevice(RegisteredDeviceEntity registeredDevice) {
        this.registeredDevice = registeredDevice;
    }
}
