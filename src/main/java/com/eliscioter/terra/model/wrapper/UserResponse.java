package com.eliscioter.terra.model.wrapper;

import com.eliscioter.terra.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private List<UserDTO> users;
    private String message;

    public UserResponse(List<UserDTO> users) {
        this.users = users;
    }

    public UserResponse(String message) {
        this.message = message;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public String getMessage() {
        return message;
    }
}
