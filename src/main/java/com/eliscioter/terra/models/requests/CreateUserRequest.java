package com.eliscioter.terra.models.requests;

import com.eliscioter.terra.commons.constants.ValidIdentifierEnum;
import com.eliscioter.terra.commons.constants.ValidPasswordEnum;
import com.eliscioter.terra.commons.validations.ValidIdentifier;
import com.eliscioter.terra.commons.validations.ValidPassword;

public record CreateUserRequest(
        @ValidIdentifier(type = ValidIdentifierEnum.USERNAME) String username,
        @ValidIdentifier(type = ValidIdentifierEnum.EMAIL) String email,
        @ValidPassword(type = ValidPasswordEnum.REGISTER) String password) {

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
