package com.project.sem4.repository.interfaces;

import com.project.sem4.model.AddressCities;
import com.project.sem4.model.AddressDistrict;
import com.project.sem4.model.AddressWards;
import com.project.sem4.model.view.AddressDistrictView;
import com.project.sem4.model.view.AddressWardView;

import java.util.List;

public interface AddressRepository {
    public List<AddressCities> getAllCity();
    public Boolean insertCities(AddressCities cities);
    public AddressCities findCitiesById(Integer Id);
    public List<AddressDistrictView> getAllDistrict();
    public Boolean insertDistrict(AddressDistrict district);
    public Boolean deleteCities(Integer Id);
    public List<AddressDistrict> findDistrictByCitiesId(Integer Id);
    public AddressDistrictView findDistrictById(Integer Id);
    public Boolean editDistrict(AddressDistrict district);
    public Boolean deletetDistrict(Integer Id);
    public List<AddressWards> findWardByDistrictId(Integer districtId);
    public Boolean editCities(AddressCities cities);
    public List<AddressWardView> getAllWards();
    public Boolean insertWard(AddressWards wards);
    public AddressWardView findWardById(Integer Id);
    public  Boolean updateWards(AddressWards wards);

}

