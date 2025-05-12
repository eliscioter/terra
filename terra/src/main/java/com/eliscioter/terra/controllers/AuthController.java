package com.eliscioter.terra.controllers;

import com.eliscioter.terra.commons.utils.JWTUtils;
import com.eliscioter.terra.controllers.interfaces.IAuthService;
import com.eliscioter.terra.implementations.impl.UserDetailsImpl;
import com.eliscioter.terra.implementations.services.AuthService;
import com.eliscioter.terra.implementations.services.RefreshTokenService;
import com.eliscioter.terra.models.entity.RefreshToken;
import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.requests.TokenRefreshRequest;
import com.eliscioter.terra.models.responses.TokenRefreshResponse;
import com.eliscioter.terra.models.wrapper.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AuthController implements IAuthService {

    @Autowired
    AuthService authService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JWTUtils jwtUtils;

    @Override
    public ResponseEntity<ResponseData> login(LoginRequest loginRequest) {
        return authService.auth(loginRequest);
    }

    @Override
    public ResponseEntity<ResponseData> refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        String token = tokenRefreshRequest.getToken();

        return refreshTokenService.findByToken(token)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtUtils.generateAccessTokenFromUsername(user.getUsername());
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseData()
                            .add("token", new TokenRefreshResponse(accessToken, token)));
                }).orElseThrow(() -> new RuntimeException("Error creating refresh token"));
    }

    @Override
    public ResponseEntity<ResponseData> logout() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        UUID userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData().add("message", "Logout Successfully"));
    }
}
