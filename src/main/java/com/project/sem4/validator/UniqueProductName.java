package com.project.sem4.validator;

import java.lang.annotation.*;
import javax.validation.*;

@Constraint(validatedBy = UniqueProductNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueProductName {
    public String message() default "Tên Sản Phẩm Này Đã Được Dùng";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default{};
}