package com.project.sem4.validator;

import javax.validation.*;

import com.project.sem4.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {

    @Autowired
    private ProductRepositoryImpl productRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && productRepository.findProductByName(value);
    }

}