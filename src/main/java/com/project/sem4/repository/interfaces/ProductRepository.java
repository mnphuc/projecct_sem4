package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Products;
import com.project.sem4.model.view.InsertProductModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductRepository {
    public List<Products> getAllProducts();
    public Boolean insertProducts(InsertProductModel insertProductModel);
    public InsertProductModel findProductById(Long Id);
    public Boolean editProduct(InsertProductModel insertProductModel);
    public Boolean deleteProduct(Integer id);
    public Products findProById(Long id);
    public Products getProductBySlug(String slug);
    public Products getProductById(Long id);
    public List<Products> getRelatedProduct(Double price);
    public List<Products> filterProductByPrice(Double minPrice, Double maxPrice);
    public List<Products> getAllProductByOder();
    
}
