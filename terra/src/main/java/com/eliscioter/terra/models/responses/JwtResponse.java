package com.eliscioter.terra.models.responses;

import com.eliscioter.terra.models.entity.RefreshToken;

import java.util.UUID;

public class JwtResponse {

    private String accessToken;
    private RefreshToken refreshToken;
    private final String tokenType = "Bearer";
    private UUID id;
    private String username;
    private String email;

    public JwtResponse(String accessToken, RefreshToken refreshToken, UUID id, String username, String email) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
