package com.project.sem4.controller;

import com.project.sem4.model.*;
import com.project.sem4.model.map.HashMapCart;
import com.project.sem4.model.service.Cart;
import com.project.sem4.model.view.CartInfo;
import com.project.sem4.model.view.RatingView;
import com.project.sem4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api")
public class ClientServiceController {
    @Autowired
    ProductRepositoryImpl productRepository;
    @Autowired
    ClientRepositoryImpl clientRepository;
    @Autowired
    AttributeRepositoryImpl attributeRepository;
    @Autowired
    AddressRepositoryImpl addressRepository;
    @Autowired
    UserRepositoryImpl userRepository;
    @RequestMapping(value = "addCart", method = RequestMethod.GET)
    public ResponseEntity<HashMap<Long, CartInfo>> addCart(HttpSession session, @ModelAttribute Cart cart) {
        Integer[] attrs = new Integer[0];
        String[] attrName = new String[0];
        List<Integer> integers = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        if (cart.getAttr() == null || cart.getAttr().length == 0){
            List<ClientRepositoryImpl.GsonOb> list = clientRepository.getAttributeByProId(cart.getProId());
            for (ClientRepositoryImpl.GsonOb gsonOb : list) {
               List<ClientRepositoryImpl.AttributeList> attributeLists = gsonOb.getAttributeList();
               ClientRepositoryImpl.AttributeList  attributeList = attributeLists.get(0);
               Integer attrId = attributeList.getId();
               String attrNames = attributeList.getName();
               strings.add(attrNames);
               attrName = strings.toArray(attrName);
               integers.add(attrId);
               attrs = integers.toArray(attrs);
            }
            cart.setAttr(attrs);
        }
        List<Attribute> attributeList = new ArrayList<>();
        Integer attrIdInt = 0;
        Integer xxx = 1;
        for (Integer attrId : cart.getAttr()){
            Attribute attribute = attributeRepository.findAttributeById(attrId);
            attributeList.add(attribute);
            attrIdInt += attrId * xxx;
            xxx *= 10;

        }
        HashMap<Long, CartInfo> cartItems = (HashMap<Long, CartInfo>) session.getAttribute("myCart");
        if (cartItems == null || cartItems.size() == 0) {
            cartItems = new HashMap<>();
        }
        Products products = productRepository.findProById(cart.getProId());
        Long keyId = cart.getProId() * xxx+ attrIdInt ;
        if (products != null) {
            CartInfo item;
                if (cartItems.containsKey(keyId) ) {
                item = cartItems.get(keyId);
                item.setProducts(products);
                item.setAttribute(attributeList);
                item.setQuantity(item.getQuantity() + cart.getQuantity());
                } else {
                    item = new CartInfo();
                    item.setProducts(products);
                    item.setAttribute(attributeList);
                    item.setQuantity(cart.getQuantity());
                }
            cartItems.put(keyId, item);
        }
        session.setAttribute("myCart", cartItems);
        return new ResponseEntity<HashMap<Long, CartInfo>>(cartItems, HttpStatus.OK);
    }
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ResponseEntity< HashMap<Long, CartInfo>> RemoveItem(ModelMap mm, HttpSession session, @RequestParam("keyId")Long keyId) {

        HashMap<Long, CartInfo> cartItems = (HashMap<Long, CartInfo>) session.getAttribute("myCart");
        if (cartItems == null || cartItems.size() == 0) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(keyId)) {
            cartItems.remove(keyId);
        }
        session.setAttribute("myCart", cartItems);
        return new ResponseEntity<HashMap<Long, CartInfo>>(cartItems, HttpStatus.OK);
    }

    @RequestMapping(value = "checkDiscount", method = RequestMethod.POST)
    public ResponseEntity<?> checkDiscount(@RequestParam("code")String code, HttpSession session) throws ParseException {
        Discount discount = clientRepository.checkDiscount(code);
        if ( discount == null || discount.getId() == null){
            return new ResponseEntity<String>("mã Giảm Giá không Tồn Tại", HttpStatus.NO_CONTENT);
        }
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNow = new Date();
        String nowDate = formatDate.format(dateNow);
        Date nowDateDiscount = formatDate.parse(nowDate);
        Date minDate = discount.getDateStart();
        Date maxDate = discount.getDateEnd();
        if (minDate.compareTo(nowDateDiscount) <= 0 && maxDate.compareTo(nowDateDiscount)>= 0) {
            System.out.println("còn hạn");
        }
        else{
            return new ResponseEntity<String>("mã Giảm Giá không Tồn Tại", HttpStatus.NO_CONTENT);
        }

        HashMap<Long, CartInfo> cartItems = (HashMap<Long, CartInfo>) session.getAttribute("myCart");
        Double totals = (Double) session.getAttribute("totals");
        String codes = (String) session.getAttribute("codeDiscount");
        if ( codes != null && !codes.isEmpty() && codes.equals(code)){
            return new ResponseEntity<String>("Bạn Đã Sử Dụng Mã Code Này Rồi", HttpStatus.NO_CONTENT);
        }
        Double totalDiscount = Double.valueOf(0);
        if (discount.getId() != null){
            Integer a = Integer.parseInt(discount.getDiscount());
                totalDiscount = totals *(100 - a)/100;
        }
        Double abc = totals - totalDiscount;
        if (abc > discount.getMaxDiscount()){
            abc = discount.getMaxDiscount();
        }
        session.setAttribute("discountPrice", abc);
        session.removeAttribute("codeDiscount");
        session.setAttribute("codeDiscount", code);
        return new ResponseEntity<Double>(abc, HttpStatus.OK);
    }
    @RequestMapping(value = "getListDistrict", method = RequestMethod.GET)
    public List<AddressDistrict> findDistrictByCitiesId(@RequestParam(value = "id") Integer id){
        return addressRepository.findDistrictByCitiesId(id);
    }
    @RequestMapping(value = "getListWard", method = RequestMethod.GET)
    public List<AddressWards> findWardByDistrictId(@RequestParam(value = "id") Integer id){
        return addressRepository.findWardByDistrictId(id);
    }
    @RequestMapping(value = "addReview", method = RequestMethod.GET)
    public List<RatingView> addReview(@RequestParam(value = "proId", required = false)Long proId, @RequestParam(value = "start", required = false)Integer start, @RequestParam(value = "comment", required = false)String comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.getUserByEmail(authentication.getName());
        Rating rating = new Rating();
        rating.setStarRating(start);
        rating.setComment(comment);
        rating.setProductId(proId);
        rating.setUserId(user.getUserID());
        Boolean bl = clientRepository.addRating(rating);
        return clientRepository.getRatingByProId(proId);
    }
}
