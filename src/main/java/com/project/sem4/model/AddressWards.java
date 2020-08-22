package com.project.sem4.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressWards {
//    Id int primary key not null,
//    Name nvarchar(255) not null,
//    Type nvarchar(255),
//    DistrictId int
    @NotNull(message="Id Xã/Phường không được để trống")
    Integer id;
    @NotEmpty(message = "Tên Xã/Phường không được để trống")
    String name;
    String type;
    @NotNull(message = "Phải Chọn Tỉnh/TP")
    Integer districtId;

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

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
