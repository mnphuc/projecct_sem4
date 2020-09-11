package com.project.sem4.controller;


import com.project.sem4.model.Users;
import com.project.sem4.repository.OrderRepositoryImpl;
import com.project.sem4.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quan-tri")
public class AdminController {
    @Autowired
    UserRepositoryImpl userRepository;
    @Autowired
    OrderRepositoryImpl orderRepository;


    @RequestMapping(value = "",method = RequestMethod.GET)
    public String Index(ModelMap model, HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Users u = userRepository.loadUserByUsername(userDetail.getUsername());
        request.getSession().setAttribute("userId", u.getUserID());
        return "admin/home";
    }
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public RedirectView redirectIndex(){
        RedirectView view = new RedirectView();
        view.setUrl("");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        return view;
    }
    @RequestMapping(value = "fileManager", method = RequestMethod.GET)
    public String fileManager(@RequestParam(value = "id", required = false)String id){
        return "admin/fileManager/home";
    }

//    @RequestMapping(value = "testMultiData", method = RequestMethod.GET)
//    public String getTestForm(Model model){
//        model.addAttribute("source", new testTn());
//        return "admin/test";
//    }
//    @RequestMapping(value = "testMultiData", method = RequestMethod.POST)
//    public String getTestFormSubmit(@ModelAttribute("SpringWeb")testTn testTn, ModelMap model){
////        model.addAttribute("username", testTn.getUsername());
////        model.addAttribute("password", testTn.getPassword());
////        model.addAttribute("address", testTn.getAddress());
////        model.addAttribute("receivePaper", testTn.isReceivePaper());
////        model.addAttribute("favoriteFrameworks", testTn.getFavoriteFrameworks());
////        return "admin/test";
//    }
//    @RequestMapping(value = "test", method = RequestMethod.GET)
//    public String test(){
//        return "admin/test";
//    }


//
//    @ModelAttribute("webFrameworkList")
//    public List<String> getWebFrameworkList() {
//        List<String> webFrameworkList = new ArrayList<String>();
//        webFrameworkList.add("Spring MVC");
//        webFrameworkList.add("Struts 1");
//        webFrameworkList.add("Struts 2");
//        webFrameworkList.add("Apache Wicket");
//        return webFrameworkList;
//}

}
