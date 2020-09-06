package com.project.sem4.service;

import com.google.gson.Gson;
import com.project.sem4.model.Categories;
import com.project.sem4.model.Products;
import com.project.sem4.model.view.ProductMapTest;
import com.project.sem4.repository.CategoriesRepositoryImpl;
import com.project.sem4.repository.ProductRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TestMap {
//    @Scheduled(fixedDelay = 5000)
//    public void printNow() {
//        //System.out.println(ThreadLocalRandom.current().nextInt());
//    }

    public static void main(String[] args) {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        CategoriesRepositoryImpl categoriesRepository = new CategoriesRepositoryImpl();

        ModelMapper modelMapper = new ModelMapper();
        ProductMapTest productMapTest1 = new ProductMapTest();

        com.project.sem4.model.view.TestMap productMapTest = modelMapper.map(productMapTest1, com.project.sem4.model.view.TestMap.class);

        List<Products> productsList = productRepository.getAllProducts();
        List<Categories> categoriesList = categoriesRepository.getAllCategories();
        List<com.project.sem4.model.view.TestMap> list = new ArrayList<>();
        for (Products products: productsList){
            ProductMapTest productMapTest12 = new ProductMapTest();
            productMapTest12.setProducts(products);
            productMapTest12.setCategoriesList(categoriesList);
            productMapTest.setProductMapTest(productMapTest12);
            list.add(productMapTest);
        }
        Long id = Long.parseLong("1");

        for (com.project.sem4.model.view.TestMap testMap : list){
            List<Categories> categories = testMap.getProductMapTest().getCategoriesList();
            for (Categories categories1 : categories){
                System.out.println(categories1.getCategoryName());
            }

        }

        Products products = productRepository.findProById(id);




//        String json = new Gson().toJson(list);
//
//            System.out.println(json);

    }
}



