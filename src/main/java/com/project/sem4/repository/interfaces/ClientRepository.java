package com.project.sem4.repository.interfaces;

import com.project.sem4.model.*;
import com.project.sem4.model.map.AttributeMap;
import com.project.sem4.model.view.RatingView;
import com.project.sem4.repository.ClientRepositoryImpl;
import sun.rmi.runtime.Log;

import java.util.List;

public interface ClientRepository {
    public List<Categories> getCategoryByProId(Long proId);
    public List<AttributeSet> getAttributeSetByProId(Long proId);
    public List<Attribute> getAttributeByProId(Long proId, Integer attrSId);
    public List<ProductAttribute> getAllAttributeByProId(Long proId);
    public List<Products> getProductByCate(String url);
    public List<ClientRepositoryImpl.GsonOb> getAttributeByProId(Long proId);
    public Discount checkDiscount(String code);
    public List<RatingView> getRatingByProId(Long proId);
    public Boolean addRating(Rating rating);
}
