package com.project.sem4.controller;


import com.project.sem4.model.Categories;
import com.project.sem4.model.Users;
import com.project.sem4.repository.CategoriesRepositoryImpl;
import com.project.sem4.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class AnnotationAdvice {
    @Autowired
    UserRepositoryImpl userRepository;
    @Autowired
    CategoriesRepositoryImpl categoriesRepository;
    @ModelAttribute("userInfo")
    public Users getUserLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = new Users();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user = userRepository.getUserByEmail(authentication.getName());
        }
        return user;
    }

    @ModelAttribute("listCate")
    public List<Categories> messages() {
        return categoriesRepository.getAllCategories();
    }
}
