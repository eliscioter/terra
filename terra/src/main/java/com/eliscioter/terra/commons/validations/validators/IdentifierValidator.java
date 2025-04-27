package com.eliscioter.terra.commons.validations.validators;

import com.eliscioter.terra.commons.constants.ValidIdentifierEnum;
import com.eliscioter.terra.commons.validations.ValidIdentifier;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class IdentifierValidator implements ConstraintValidator<ValidIdentifier, String> {
    private ValidIdentifierEnum[] type;

    @Override
    public void initialize(ValidIdentifier constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String identifier, ConstraintValidatorContext context) {
        if (identifier == null || identifier.isEmpty() || identifier.isBlank()) {
            if (type.length < 2) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("%s is required", type[0].name())).addConstraintViolation();
            }
            return false;
        }

        boolean isValid = true;

        for (ValidIdentifierEnum identifierType : type) {
            switch (identifierType) {
                case EMAIL: {
                    if (!isValidEmail(identifier)) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate("Invalid email address format").addConstraintViolation();
                        isValid = false;
                    } else {
                        isValid = true;
                    }
                    break;
                }
                case USERNAME: {
                    if (!isValidUsername(identifier)) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(
                                "Username length should be between 2 and 20 characters long")
                                .addConstraintViolation();
                        isValid = false;
                    } else {
                        isValid = true;
                    }
                    break;
                }
            }
        }
        return isValid;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

        return PATTERN.matcher(email).matches();
    }

    private boolean isValidUsername(String username) {
        return username.length() >= 2 && username.length() <= 20;
    }
}
