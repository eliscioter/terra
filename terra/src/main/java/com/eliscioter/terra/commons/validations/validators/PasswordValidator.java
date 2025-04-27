package com.eliscioter.terra.commons.validations.validators;

import com.eliscioter.terra.commons.constants.ValidPasswordEnum;
import com.eliscioter.terra.commons.validations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    ValidPasswordEnum type;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
       boolean isValid = true;

        if (password == null || password.isEmpty() || password.isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password is required").addConstraintViolation();
            return false;
        }

        switch (type) {
            case LOGIN: {
                break;
            }
            case REGISTER: {
                isValid = password.length() >= 8 && password.length() <= 55;
                break;
            }
        }
        return isValid;
    }
}
