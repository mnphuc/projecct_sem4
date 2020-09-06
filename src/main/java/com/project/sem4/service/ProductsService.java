package com.project.sem4.service;

import com.project.sem4.model.*;
import com.project.sem4.model.map.AttributeGroupMap;

import com.project.sem4.model.map.ProductViewMap;
import com.project.sem4.repository.ClientRepositoryImpl;
import com.project.sem4.repository.ProductRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    ProductRepositoryImpl productRepository;
    @Autowired
    ClientRepositoryImpl clientRepository;

    public Page<Products> findPaginated(Pageable pageable, String checkCate) {
        List<Products> books = new ArrayList<>();
        if (!checkCate.isEmpty()){
            books = clientRepository.getProductByCate(checkCate);
        }else {
            books = productRepository.getAllProducts();
        }

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Products> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<Products> bookPage
                = new PageImpl<Products>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }
    public Page<ProductViewMap> getListProduct(Pageable pageable){
        List<ProductViewMap> listProduct = new ArrayList<>();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ProductViewMap> list;
        ModelMapper modelMapper = new ModelMapper();
        AttributeGroupMap attributeGroupMap = new AttributeGroupMap();
        ProductViewMap productViewMap = modelMapper.map(attributeGroupMap, ProductViewMap.class);
        List<Products> productsList = productRepository.getAllProducts();
        for (Products products: productsList){
            List<Categories> categoriesList = clientRepository.getCategoryByProId(products.getId());
            List<AttributeGroupMap> attributeGroupMap1 = new ArrayList<>();
            List<AttributeSet> attributeSets = clientRepository.getAttributeSetByProId(products.getId());
            for (AttributeSet attributeSet : attributeSets){
                AttributeGroupMap attributeGroupMap2 = new AttributeGroupMap();
                attributeGroupMap2.setAttributeSet(attributeSet);
                List<Attribute> attributeList = clientRepository.getAttributeByProId(products.getId(), attributeSet.getId());

                attributeGroupMap2.setAttributes(attributeList);
                attributeGroupMap1.add(attributeGroupMap2);
            }
            productViewMap.setProducts(products);
            productViewMap.setCategoriesList(categoriesList);

            productViewMap.setAttributeGroupMaps(attributeGroupMap1);
            listProduct.add(productViewMap);

        }

        if (listProduct.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listProduct.size());
            list = listProduct.subList(startItem, toIndex);
        }

        Page<ProductViewMap> listPageProduct
                = new PageImpl<ProductViewMap>(list, PageRequest.of(currentPage, pageSize), listProduct.size());

        return listPageProduct;
    }
}
