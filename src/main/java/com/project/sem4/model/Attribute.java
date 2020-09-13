package com.project.sem4.model;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class Attribute {
    Integer id;
    @NotNull(message = "Phải Chọn Thuộc Tính")
    Integer attributeSetId;
    @NotEmpty(message = "Mô Tả không được để trống")
    String description;
    @NotEmpty(message = "Tên Không Được Để Trống")
    String name;
    String dataType;
    String defaultValue;
}
