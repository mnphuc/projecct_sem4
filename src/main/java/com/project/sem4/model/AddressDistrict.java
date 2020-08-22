package com.project.sem4.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddressDistrict {
    @NotNull(message="Id Quận/Huyện thành không được để trống")
    private Integer id;
    @NotEmpty(message = "Tên Quận/Huyện không được để trống")
    private String name;
    private String type;
    @NotNull(message = "Phải Chọn Tỉnh/TP")
    private Integer citiesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCitiesId() {
        return citiesId;
    }

    public void setCitiesId(Integer citiesId) {
        this.citiesId = citiesId;
    }
}
