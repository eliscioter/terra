package com.eliscioter.terra.models.dto;

import com.eliscioter.terra.models.entity.UserEntity;

import java.util.UUID;

public record UserDTO(UUID id, String username, String email) {

    public static UserDTO fromUser(UserEntity user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}
