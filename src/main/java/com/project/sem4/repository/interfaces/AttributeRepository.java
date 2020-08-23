package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.AttributeSet;
import com.project.sem4.model.view.AttributeSetAttributeView;

import java.util.List;

public interface AttributeRepository {
    public List<AttributeSet> getAllAttributeSet();
    public Boolean insertAttributeSet(AttributeSet attributeSet);
    public Boolean updateAttributeSet(AttributeSet attributeSet);
    public AttributeSet findAttributeSetById(Integer id);
    public Boolean deleteAttributeSet(Integer id);
    public List<Attribute> getAllAttribute();
    public List<Attribute> findAttributeByAttributeSetId(Integer id);
    public Boolean createAttribute(Attribute attribute);
    public Boolean updateAttribute(Attribute attribute);
    public Attribute findAttributeById(Integer id);
    public Boolean deleteAttribute(Integer id);
    public String getListAttributeSetAttributeViews();
}
