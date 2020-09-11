package com.project.sem4.validator;

import com.project.sem4.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailUserValidator implements ConstraintValidator<UniqueEmailUser, String> {

    @Autowired
    UserRepositoryImpl userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && userRepository.findUserByEmail(value);
    }
}