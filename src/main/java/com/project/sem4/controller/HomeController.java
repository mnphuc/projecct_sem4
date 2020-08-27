package com.project.sem4.controller;


import com.project.sem4.model.Banner;
import com.project.sem4.model.Categories;
import com.project.sem4.model.Products;
import com.project.sem4.repository.AttributeRepositoryImpl;
import com.project.sem4.repository.BannerRepositoryImpl;
import com.project.sem4.repository.CategoriesRepositoryImpl;
import com.project.sem4.repository.ProductRepositoryImpl;
import com.sun.xml.internal.stream.events.AttributeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    AttributeRepositoryImpl attribute;
    @Autowired
    BannerRepositoryImpl bannerRepository;
    @Autowired
    ProductRepositoryImpl productRepository;

    @Autowired
    CategoriesRepositoryImpl categoriesRepository;
    @RequestMapping( value = "endPoints", method = RequestMethod.GET )
    public String getEndPointsInView( Model model )
    {
        model.addAttribute( "endPoints", requestMappingHandlerMapping.getHandlerMethods().keySet());
        return "hello";
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String Index(ModelMap model){
        List<Banner> listBanner = bannerRepository.getAllBanner();
        model.addAttribute("listBanner", listBanner);
        List<Products> productsList = productRepository.getAllProducts();
        model.addAttribute("listProduct", productsList);
        List<Categories> categories = categoriesRepository.getAllCategories();
        List<Categories> newlist = categories.stream().limit(3).collect(Collectors.toList());
        model.addAttribute("listCategory", newlist);
        return "home";
    }


    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("name", name);

        return "hello";
    }
    @RequestMapping(value = "san-pham/{slug}", method = RequestMethod.GET)
    public String viewProduct(@PathVariable("slug")String slug){

        return "";
    }
    @RequestMapping(value = "danh-sach-san-pham", method = RequestMethod.GET)
    public String viewListProduct(Model model){
        return "shopList";
    }
//    @GetMapping("/admin")
//    public String admin(){
//        return "admin/index";
//    }
}
