package com.eliscioter.terra.models.requests;


import com.eliscioter.terra.commons.constants.ValidIdentifierEnum;
import com.eliscioter.terra.commons.constants.ValidPasswordEnum;
import com.eliscioter.terra.commons.validations.ValidIdentifier;
import com.eliscioter.terra.commons.validations.ValidPassword;

public record LoginRequest(
        @ValidIdentifier(type = {ValidIdentifierEnum.EMAIL, ValidIdentifierEnum.USERNAME}) String identifier,
        @ValidPassword(type = ValidPasswordEnum.LOGIN) String password) {

    public LoginRequest (String identifier, String password){
        this.identifier = identifier;
        this.password = password;
    }

    @Override
    public String identifier() {
        return identifier;
    }

    @Override
    public String password() {
        return password;
    }
}