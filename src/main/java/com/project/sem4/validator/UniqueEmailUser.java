package com.project.sem4.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailUserValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueEmailUser {
    public String message() default "Email Này Đã Được Sử Dụng";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default{};
}
