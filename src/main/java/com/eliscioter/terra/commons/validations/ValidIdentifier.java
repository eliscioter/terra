package com.eliscioter.terra.commons.validations;

import com.eliscioter.terra.commons.constants.ValidIdentifierEnum;
import com.eliscioter.terra.commons.validations.validators.IdentifierValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdentifierValidator.class)
public @interface ValidIdentifier {
    String message() default "Invalid identifier";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    ValidIdentifierEnum[] type() default ValidIdentifierEnum.EMAIL;
}
