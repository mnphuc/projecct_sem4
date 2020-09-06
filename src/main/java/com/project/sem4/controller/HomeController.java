package com.project.sem4.controller;


import com.google.gson.Gson;
import com.project.sem4.model.Banner;
import com.project.sem4.model.Categories;
import com.project.sem4.model.Products;
import com.project.sem4.model.map.AttributeMap;
import com.project.sem4.model.map.ProductViewMap;
import com.project.sem4.model.service.Cart;
import com.project.sem4.model.service.Mail;
import com.project.sem4.model.view.CartInfo;
import com.project.sem4.repository.*;
import com.project.sem4.service.MailService;
import com.project.sem4.service.ProductsService;
import com.sun.xml.internal.stream.events.AttributeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    ProductsService productService;
    @Autowired
    CategoriesRepositoryImpl categoriesRepository;
    @Autowired
    ClientRepositoryImpl clientRepository;
    @ModelAttribute("listCate")
    public List<Categories> messages() {
        return categoriesRepository.getAllCategories();
    }

//    @RequestMapping( value = "endPoints", method = RequestMethod.GET )
//    public String getEndPointsInView( Model model )
//    {
//        model.addAttribute( "endPoints", requestMappingHandlerMapping.getHandlerMethods().keySet());
//        return "hello";
//    }
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
    public String viewProduct(@PathVariable("slug")String slug, Model model){
        Products products = productRepository.getProductBySlug(slug);
        List<Categories> categoriesList = clientRepository.getCategoryByProId(products.getId());
        model.addAttribute("product", products);
        model.addAttribute("category", categoriesList);
        List<ClientRepositoryImpl.GsonOb> attributeMaps = clientRepository.getAttributeByProId(products.getId());
        model.addAttribute("attribute", attributeMaps);
        List<Products> productsList = productRepository.getRelatedProduct(products.getPrice());
        List<Products> newlist = productsList.stream().limit(5).collect(Collectors.toList());
        model.addAttribute("productRelated", newlist);
        model.addAttribute("productRs", productsList);
        return "productDetail";
    }
    @RequestMapping(value = "danh-muc/{url}", method = RequestMethod.GET)
    public String getCategory(@PathVariable("url")String url, Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Products> ListProduct = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize), url);


        model.addAttribute("listProduct", ListProduct);
        int totalPages = ListProduct.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "shopList";
    }


    @RequestMapping(value = "danh-sach-san-pham", method = RequestMethod.GET)
    public String viewListProduct(Model model,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Products> ListProduct = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize), "");


        model.addAttribute("listProduct", ListProduct);
        List<Categories> categoriesList = categoriesRepository.getAllCategories();
        model.addAttribute("category", categoriesList);
        int totalPages = ListProduct.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "shopList";
    }
    @RequestMapping(value = "gio-hang", method = RequestMethod.GET)
    public String viewCart(Model model, HttpSession session){
        HashMap<Long, CartInfo> cartItems = (HashMap<Long, CartInfo>) session.getAttribute("myCart");
        Double total = Double.valueOf(0);
        if (cartItems == null || cartItems.size() == 0){
            return "redirect:/danh-sach-san-pham";
        }
        if (cartItems != null){
            for (CartInfo cartInfo : cartItems.values()){
                total += cartInfo.getProducts().getPriceSale() * cartInfo.getQuantity();
            }
        }
        Double priceShip = Double.valueOf(25000);
        if (total >= 1000000){
            priceShip = Double.valueOf(0);
        }
        Double totals = Double.valueOf(0);

        model.addAttribute("totalCart", total);
        session.setAttribute("totals", total);
        model.addAttribute("priceShip", priceShip);
        Double totalDiscount = (Double) session.getAttribute("discountPrice");
        if (totalDiscount == null){
            totalDiscount = Double.valueOf(0);
        }
        if (totalDiscount == null){
            totals = total + priceShip;
        }else {
            totals = total +priceShip - totalDiscount;
        }
        model.addAttribute("totals", totals);
        model.addAttribute("totalDiscount", totalDiscount);
        return "viewCart";
    }
    @Autowired
    private MailService mailService;
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testMail() throws IOException, MessagingException {
        Mail mail = new Mail();
        mail.setEmail("phucmnp@gmail.com");
        mail.setObject("anh nhứ e");
        mail.setMessage("ahihi đồ óc chó");
        mailService.sendMail(mail);
        return "";
    }
}
