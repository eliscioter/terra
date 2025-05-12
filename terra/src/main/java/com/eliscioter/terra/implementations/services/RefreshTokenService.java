package com.eliscioter.terra.implementations.services;

import com.eliscioter.terra.models.entity.RefreshToken;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(UUID userId);
    RefreshToken verifyExpiration(RefreshToken token);

    @Transactional
    void deleteByUserId(UUID userId);
}
