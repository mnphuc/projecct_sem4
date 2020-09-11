package com.project.sem4.validator;

import com.project.sem4.repository.CategoriesRepositoryImpl;
import com.project.sem4.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {

    @Autowired
    private CategoriesRepositoryImpl categoriesRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && categoriesRepository.findCategoyByName(value);
    }

}