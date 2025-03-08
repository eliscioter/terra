package com.eliscioter.terra.implementations.impl;

import com.eliscioter.terra.implementations.services.AuthService;
import com.eliscioter.terra.models.dto.UserDTO;
import com.eliscioter.terra.models.entity.UserEntity;
import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import com.eliscioter.terra.repositories.UserRepository;
import com.eliscioter.terra.commons.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthImpl implements AuthService {

    UserRepository userRepository;

    public AuthImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ResponseData> auth(LoginRequest loginRequest) {
        return isVerifiedUser(loginRequest);
    }

    private ResponseEntity<ResponseData> isVerifiedUser(LoginRequest loginRequest) {
        Optional<UserEntity> user = userRepository
                .findByUsername(loginRequest.identifier())
                .or(() -> userRepository.findByEmail(loginRequest.identifier()));

        if (user.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body( new ResponseData().add("message", "Invalid Credentials"));
        }

        if (!isUserPassword(user.get().getPassword(), loginRequest.password())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body( new ResponseData().add("message", "Invalid Credentials"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new ResponseData().add("user", UserDTO.fromUser(user.get())));
    }

    private boolean isUserPassword(String savedPassword, String password) {
        return Util.verifyPassword(savedPassword, password);
    }
}
