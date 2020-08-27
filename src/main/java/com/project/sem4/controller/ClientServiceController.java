package com.project.sem4.controller;

import com.project.sem4.model.Products;
import com.project.sem4.model.view.CartInfo;
import com.project.sem4.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ClientServiceController {
    @Autowired
    ProductRepositoryImpl productRepository;

    @RequestMapping(value = "addCart/{productId}", method = RequestMethod.GET)
    public ResponseEntity<String> addCart(HttpSession session, @PathVariable("productId") Long productId, @RequestParam(value = "Attr",required = false )String attr){
        HashMap<Long, CartInfo> cartItems = (HashMap<Long, CartInfo>) session.getAttribute("myCart");
        if (cartItems == null){
            cartItems = new HashMap<>();
        }

        Products products = productRepository.findProById(productId);
        if (products != null){
            CartInfo item;
            if (cartItems.containsKey(productId)) {
                item = cartItems.get(productId);
                item.setProducts(products);
                item.setQuantity(item.getQuantity() + 1);
            }else {
                item = new CartInfo();
                item.setProducts(products);
                item.setQuantity(1);
            }
            cartItems.put(productId, item);

        }
        return new ResponseEntity<>("thêm thành công", HttpStatus.OK);
    }
}
