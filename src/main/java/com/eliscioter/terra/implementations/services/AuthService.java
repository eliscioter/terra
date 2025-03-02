package com.eliscioter.terra.implementations.services;

import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;

public interface AuthService {
    ResponseData auth(LoginRequest loginRequest);
}
