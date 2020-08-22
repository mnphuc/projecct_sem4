package com.project.sem4.service;

import com.project.sem4.model.AddressCities;
import com.project.sem4.model.AddressDistrict;
import com.project.sem4.model.AddressWards;
import com.project.sem4.model.view.AddressDistrictView;
import com.project.sem4.repository.AddressRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressCitiesService {
    @Autowired
    AddressRepositoryImpl addressRepository;
    // lớp xử lý logic
    public Boolean insertCity(AddressCities cities){
        Boolean bl;
        if (addressRepository.findCitiesById(cities.getId()) == null){
            bl = false;
        }
        bl = addressRepository.insertCities(cities);
        return bl;
    }
    public Boolean deleteCities(Integer Id){
        Boolean bl = false;
        List<AddressDistrict> districts = addressRepository.findDistrictByCitiesId(Id);
        if (districts.isEmpty()){
            addressRepository.deleteCities(Id);
            bl = true;
        }
        return bl;
    }
    public Boolean deleteDistrict(Integer Id){
        Boolean bl = false;
        List<AddressWards> wards = addressRepository.findWardByDistrictId(Id);
        if (wards.isEmpty()){
            addressRepository.deletetDistrict(Id);
            bl = true;
        }
        return bl;
    }
    // check insert district
    public Boolean insertDistrictService(AddressDistrict district){
        Boolean bl;
        if (addressRepository.findDistrictById(district.getId()) == null){
            bl = false;
        }
        bl = addressRepository.insertDistrict(district);
        return bl;
    }
    public Boolean insertWards(AddressWards wards){
        Boolean bl;
        if (addressRepository.findWardById(wards.getId()) == null){
            bl =false;
        }
        bl = addressRepository.insertWard(wards);
        return bl;
    }

}
