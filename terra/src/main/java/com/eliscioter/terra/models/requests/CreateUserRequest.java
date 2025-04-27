package com.eliscioter.terra.models.requests;

import com.eliscioter.terra.commons.constants.ValidIdentifierEnum;
import com.eliscioter.terra.commons.constants.ValidPasswordEnum;
import com.eliscioter.terra.commons.validations.ValidIdentifier;
import com.eliscioter.terra.commons.validations.ValidPassword;

public class CreateUserRequest {
    @ValidIdentifier(type = ValidIdentifierEnum.USERNAME)
    String username;
    @ValidIdentifier(type = ValidIdentifierEnum.EMAIL)
    String email;
    @ValidPassword(type = ValidPasswordEnum.REGISTER)
    String password;

    public CreateUserRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
