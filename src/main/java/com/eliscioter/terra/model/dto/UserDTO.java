package com.eliscioter.terra.model.dto;

import com.eliscioter.terra.model.entity.UserEntity;

public class UserDTO {
    private String username;
    private String email;

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public static UserDTO fromUser(UserEntity user) {
        return new UserDTO(user.getUsername(), user.getEmail());
    }
}
