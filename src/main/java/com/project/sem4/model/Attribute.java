package com.project.sem4.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Attribute {
    Integer id;
    @NotNull(message = "Phải Chọn Thuộc Tính")
    Integer attributeSetId;
    @NotEmpty(message = "Mô Tả không được để trống")
    String description;
    @NotEmpty(message = "Tên Không Được Để Trống")
    String name;
    @NotNull(message = "Phải Chọn Kiểu Dữ Liệu")
    String dataType;
    @NotNull(message = "Phải Điền Giá Trị Của Thuộc Tính")
    String defaultValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttributeSetId() {
        return attributeSetId;
    }

    public void setAttributeSetId(Integer attributeSetId) {
        this.attributeSetId = attributeSetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
