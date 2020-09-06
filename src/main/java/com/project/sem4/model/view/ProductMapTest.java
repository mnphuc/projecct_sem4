package com.project.sem4.model.view;

import com.project.sem4.model.Categories;
import com.project.sem4.model.Products;
import lombok.Data;

import java.util.List;

@Data
public class ProductMapTest {
    private Products products;
    private List<Categories> categoriesList;
}
