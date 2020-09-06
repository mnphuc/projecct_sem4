package com.project.sem4.model.view;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.AttributeSet;


import java.util.List;

public class AttributeSetAttributeView {
   private List<AttributeSet> attributeSets;
    private List<Attribute> attributes;

    public List<AttributeSet> getAttributeSets() {
        return attributeSets;
    }

    public void setAttributeSets(List<AttributeSet> attributeSets) {
        this.attributeSets = attributeSets;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
