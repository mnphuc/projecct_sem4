package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Products;
import com.project.sem4.model.view.InsertProductModel;

import java.util.List;

public interface ProductRepository {
    public List<Products> getAllProducts();
    public Boolean insertProducts(InsertProductModel insertProductModel);

}
