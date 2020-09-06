package com.project.sem4.model.map;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.Categories;
import com.project.sem4.model.Products;
import lombok.Data;

import java.util.List;
@Data
public class ProductViewMap {
    private Products products;
    private List<Categories> categoriesList;
    private List<AttributeGroupMap> attributeGroupMaps;
}
