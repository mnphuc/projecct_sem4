package com.project.sem4.model.view;

import lombok.Data;


public class AddressDistrictView {
    private Integer id;
    private String name;
    private String type;
    private Integer citiesId;
    private String cityName;

    public Integer getCitiesId() {
        return citiesId;
    }

    public void setCitiesId(Integer citiesId) {
        this.citiesId = citiesId;
    }

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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
