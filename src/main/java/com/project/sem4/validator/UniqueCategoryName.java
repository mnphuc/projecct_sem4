package com.project.sem4.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCategoryNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueCategoryName {
    public String message() default "Tên Danh Mục Này Đã Tồn Tại";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default{};
}