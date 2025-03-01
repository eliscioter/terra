package com.eliscioter.terra.models.requests;

public record CreateUserRequest(String username, String email, String password) {

    public CreateUserRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }
}
