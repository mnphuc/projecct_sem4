package com.project.sem4.model;

import lombok.Data;


import javax.validation.constraints.NotNull;
@Data
public class Attribute {
    Integer id;
    @NotNull(message = "Phải Chọn Thuộc Tính")
    Integer attributeSetId;
    @NotNull(message = "Mô Tả không được để trống")
    String description;
    @NotNull(message = "Tên Không Được Để Trống")
    String name;
    @NotNull(message = "Phải Chọn Kiểu Dữ Liệu")
    String dataType;
    @NotNull(message = "Phải Điền Giá Trị Của Thuộc Tính")
    String defaultValue;
}
