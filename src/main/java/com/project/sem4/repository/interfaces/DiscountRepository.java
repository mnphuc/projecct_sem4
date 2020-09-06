package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Discount;

import java.util.List;

public interface DiscountRepository {
    public List<Discount> getAllDiscount();
    public Boolean insertDiscount(Discount discount);
    public Discount findDiscountById(Integer id);
    public Boolean editDiscount(Discount discount);
    public Boolean deleteDiscount(Integer id);
}
