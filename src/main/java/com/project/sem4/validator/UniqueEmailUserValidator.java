package com.project.sem4.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailUserValidator implements ConstraintValidator<UniqueEmailUser, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return false;
    }
}
