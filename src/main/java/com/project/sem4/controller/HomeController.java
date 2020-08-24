package com.project.sem4.controller;


import com.project.sem4.model.Banner;
import com.project.sem4.repository.AttributeRepositoryImpl;
import com.project.sem4.repository.BannerRepositoryImpl;
import com.sun.xml.internal.stream.events.AttributeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    AttributeRepositoryImpl attribute;
    @Autowired
    BannerRepositoryImpl bannerRepository;
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
        return "home";
    }


    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("name", name);

        return "hello";
    }
//    @GetMapping("/admin")
//    public String admin(){
//        return "admin/index";
//    }
}
