package com.eliscioter.terra.models.requests;

import com.eliscioter.terra.commons.validations.ValidPassword;

public class UpdateUserRequest extends CreateUserRequest {
    @ValidPassword
    private String oldPassword;

    public UpdateUserRequest(String username, String email, String password) {
        super(username, email, password);
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
