package com.project.sem4.model.map;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.AttributeSet;
import lombok.Data;

import java.util.List;
@Data
public class AttributeGroupMap {
    private AttributeSet attributeSet;
    private List<Attribute> attributes;
}
