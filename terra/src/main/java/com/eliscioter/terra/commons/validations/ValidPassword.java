package com.eliscioter.terra.commons.validations;

import com.eliscioter.terra.commons.constants.ValidPasswordEnum;
import com.eliscioter.terra.commons.validations.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Password must be between 8 and 55 characters long";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    ValidPasswordEnum type() default ValidPasswordEnum.LOGIN;
}
