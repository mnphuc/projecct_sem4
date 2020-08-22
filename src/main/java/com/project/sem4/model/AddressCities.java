package com.project.sem4.model;


import javax.validation.constraints.*;

public class AddressCities {
    @NotNull(message="Id tỉnh thành không được để trống")
    private Integer id;
    @NotEmpty(message = "Tên Tỉnh/TP Không Được để trống")
    private String name;
    private String type;

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
}
