package com.project.sem4.controller;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.Categories;
import com.project.sem4.model.ProductAttribute;
import com.project.sem4.model.Products;
import com.project.sem4.model.view.InsertProductModel;
import com.project.sem4.repository.AttributeRepositoryImpl;
import com.project.sem4.repository.CategoriesRepositoryImpl;
import com.project.sem4.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("quan-tri/san-pham")
public class ProductController {
    @Autowired
    CategoriesRepositoryImpl categoriesRepository;
    @Autowired
    AttributeRepositoryImpl attributeRepository;
    @Autowired
    ProductRepositoryImpl productRepository;
    @RequestMapping(value = "danh-sach-san-pham", method = RequestMethod.GET)
    public String getAllProduct(Model model){
        //productRepository.testJson();
        List<Products> list = productRepository.getAllProducts();
        model.addAttribute("list", list);
        return "admin/product/listProduct";
    }

    @RequestMapping(value = "them-san-pham", method = RequestMethod.GET)
    public String insertProductForm(ModelMap model){
        InsertProductModel insertProductModel = new InsertProductModel();
        List<Attribute> listAttributes = attributeRepository.getAllAttribute();
        model.addAttribute("insertProductModel",  new InsertProductModel());
        List<Categories> listCategories = categoriesRepository.getAllCategories();


        model.addAttribute("listCate", listCategories);
        model.addAttribute("listAttr", listAttributes);
        return "admin/product/insertProduct";
    }
    @RequestMapping(value = "them-san-pham", method = RequestMethod.POST)
    public String insertProductSubmit(@Valid InsertProductModel productModel , Model model) {

        Boolean bl = productRepository.insertProducts(productModel);
        if (bl) {

        }
        return "";
    }
    @RequestMapping(value = "sua-san-pham", method = RequestMethod.GET)
    public String editProductForm(@RequestParam("id")Integer id){
        return "";
    }
}
