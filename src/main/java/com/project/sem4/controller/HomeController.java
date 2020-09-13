package com.project.sem4.controller;


import com.google.gson.Gson;
import com.project.sem4.model.*;
import com.project.sem4.model.map.AttributeMap;
import com.project.sem4.model.map.OrderDetailMap;
import com.project.sem4.model.map.ProductViewMap;
import com.project.sem4.model.service.Cart;
import com.project.sem4.model.service.ListTask;
import com.project.sem4.model.service.Mail;
import com.project.sem4.model.users.User;
import com.project.sem4.model.view.*;
import com.project.sem4.repository.*;
import com.project.sem4.service.CheckOutService;
import com.project.sem4.service.CheckTest;
import com.project.sem4.service.MailService;
import com.project.sem4.service.ProductsService;
import com.sun.xml.internal.stream.events.AttributeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
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
    @Autowired
    AddressRepositoryImpl addressRepository;
    @Autowired
    PaymentRepositoryImpl paymentRepository;
    @Autowired
    UserRepositoryImpl userRepository;
    @Autowired
    OrderRepositoryImpl orderRepository;
    @Autowired
    BlogRepositoryImpl blogRepository;
    @Autowired
    CheckOutService checkOutService;
    @Autowired
    MailService mailService;

//    @RequestMapping( value = "endPoints", method = RequestMethod.GET )
//    public String getEndPointsInView( Model model )
//    {
//        model.addAttribute( "endPoints", requestMappingHandlerMapping.getHandlerMethods().keySet());
//        return "hello";
//    }
    static class SortByDate implements Comparator<BlogView> {
        @Override
        public int compare(BlogView f, BlogView ff) {
            return ff.getCreateAt().compareTo(f.getCreateAt());
        }
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String Index(ModelMap model){
        List<Banner> listBanner = bannerRepository.getAllBanner();
        model.addAttribute("listBanner", listBanner);
        List<Products> productsList = productRepository.getAllProducts();
        model.addAttribute("listProduct", productsList);
        List<Categories> categories = categoriesRepository.getAllCategories();
        List<Categories> newlist = categories.stream().limit(3).collect(Collectors.toList());
        List<Products> productsList1 = productRepository.getAllProductByOder();
        model.addAttribute("productPay", productsList1);
        model.addAttribute("listCategory", newlist);
        List<BlogView> blogsList = blogRepository.getAllBlog();
        Collections.sort(blogsList, new SortByDate());
        model.addAttribute("blogsList", blogsList);
        return "home";
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
        List<RatingView> ratingViewList = clientRepository.getRatingByProId(products.getId());
        model.addAttribute("rating", ratingViewList);
        return "productDetail";
    }
    @RequestMapping(value = "danh-muc/{url}", method = RequestMethod.GET)
    public String getCategory(@PathVariable("url")String url, Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Products> ListProduct = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize), url);

        List<Categories> categoriesList = categoriesRepository.getAllCategories();
        model.addAttribute("category", categoriesList);
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
    @RequestMapping(value = "san-pham-theo-gia",method = RequestMethod.GET)
    public String filterProductByPrice(Model model,
                                       @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size,
                                       @RequestParam(value = "minPrice", required = false)Double minPrice,
                                       @RequestParam(value = "maxPrice", required = false)Double maxPrice){
        if (minPrice == null || maxPrice == null){
            return "redirect:/danh-sach-san-pham";
        }

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Products> ListProduct = productService.filterProductByPrice(PageRequest.of(currentPage - 1, pageSize), minPrice, maxPrice);


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
    @RequestMapping(value = "tim-kiem-san-pham", method = RequestMethod.GET)
    public String viewListProductSearce(Model model,@RequestParam(value = "q", required = false) String q,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Products> ListProduct = productService.findPaginatedSearce(PageRequest.of(currentPage - 1, pageSize), q);
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

    @RequestMapping(value = "bai-viet/{slug}", method = RequestMethod.GET)
    public String viewBlog(Model model, @PathVariable("slug")String slug){
        BlogView blogView = blogRepository.getBlogBySlug(slug);
        model.addAttribute("detailBlog", blogView);
        return "blogDetail";
    }
    @RequestMapping(value = "thanh-toan", method = RequestMethod.GET)
    public String viewCheckout(Model model, HttpSession session){
        List<AddressCities> citiesList = addressRepository.getAllCity();
        model.addAttribute("listCity", citiesList);
        List<Payment>paymentList = paymentRepository.getAllPayment();
        model.addAttribute("listPayment", paymentList);
        model.addAttribute("order", new InsertOrderView());
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
        return "checkout";
    }

    @RequestMapping(value = "thanh-toan", method = RequestMethod.POST)
    public String submitCheckOut(@Valid InsertOrderView insertOrderView, Model model, RedirectAttributes redirectAttributes){
        checkOutService.checkout(insertOrderView);
        return "redirect:/";
    }


    @RequestMapping(value = "lich-su-mua-hang", method = RequestMethod.GET)
    public String viewOrderHistory(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.getUserByEmail(authentication.getName());
        List<OrderView> list = orderRepository.getOrdrByuserId(user.getUserID());
        model.addAttribute("listOrder", list);
        return "oderHistory";
    }
    @RequestMapping(value = "chi-tiet-don-hang/{idOrder}", method = RequestMethod.GET)
    public String viewDetailOrder(@PathVariable("idOrder")Integer idOrder, Model model){
        List<OrderDetailMap> listOrderDetail = orderRepository.getAllOrderDetail(idOrder);
        OrderView  orderView = orderRepository.getOrderById(idOrder);
        model.addAttribute("order", orderView);
        model.addAttribute("orderDetail", listOrderDetail);
        Double totalPrice = Double.valueOf(0);
        Double totalOrder = Double.valueOf(0);
        for (OrderDetailMap detail : listOrderDetail){
            totalPrice += detail.getProducts().getPriceSale() * detail.getOrderDetail().getTotal();
            totalOrder += detail.getOrderDetail().getPrice();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalOrder", totalOrder);
        return "orderDetail";
    }
    @RequestMapping(value = "thong-tin", method = RequestMethod.GET)
    public String aboutShop(){
        return "about";
    }
    @RequestMapping(value = "lien-he", method = RequestMethod.GET)
    public String viewContact(){
        return "contact";
    }

}
