package com.project.sem4.controller;

import com.project.sem4.model.*;
import com.project.sem4.model.view.InsertProductModel;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.AttributeRepositoryImpl;
import com.project.sem4.repository.CategoriesRepositoryImpl;
import com.project.sem4.repository.ProductRepositoryImpl;
import com.project.sem4.vendor.MnpSlug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public String getAllProduct(Model model, @ModelAttribute("msg") String message){
        //productRepository.testJson();
        List<Products> list = productRepository.getAllProducts();
        model.addAttribute("list", list);
        if (!message.isEmpty()){
            List<Message> list1 = new ArrayList<>();
            String[] arr = message.split(",");
            Message message1 = new Message();
            message1.setKey(arr[0]);
            message1.setType(arr[1]);
            message1.setValue(arr[2]);
            message1.setCheck(arr[3]);
            list1.add(message1);
            model.addAttribute("msg", list1);
        }
        return "admin/product/listProduct";
    }

    @RequestMapping(value = "them-san-pham", method = RequestMethod.GET)
    public String insertProductForm(ModelMap model){
        InsertProductModel insertProductModel = new InsertProductModel();

        model.addAttribute("insertProductModel",  new InsertProductModel());
        List<Attribute> listAttributes = attributeRepository.getAllAttribute();
        List<Categories> listCategories = categoriesRepository.getAllCategories();
        List<AttributeSet> listAttrSet = attributeRepository.getAllAttributeSet();
        String listJson = attributeRepository.getListAttributeSetAttributeViews();
        model.addAttribute("listCate", listCategories);
        model.addAttribute("listAttr", listAttributes);
        model.addAttribute("listAttrSet", listAttrSet);
        model.addAttribute("listJson", listJson);
        return "admin/product/insertProduct";
    }
    @RequestMapping(value = "them-san-pham", method = RequestMethod.POST)
    public String insertProductSubmit(@Valid InsertProductModel insertProductModel, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            List<Attribute> listAttributes = attributeRepository.getAllAttribute();
            List<Categories> listCategories = categoriesRepository.getAllCategories();
            List<AttributeSet> listAttrSet = attributeRepository.getAllAttributeSet();
            String listJson = attributeRepository.getListAttributeSetAttributeViews();
            model.addAttribute("listCate", listCategories);
            model.addAttribute("listAttr", listAttributes);
            model.addAttribute("listAttrSet", listAttrSet);
            model.addAttribute("listJson", listJson);
            return "admin/product/insertProduct";
        }
        String slug = MnpSlug.makeSlug(insertProductModel.getProductName());
        insertProductModel.setSlug(slug);
        Boolean bl = productRepository.insertProducts(insertProductModel);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới SẢn Phẩm Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/san-pham/danh-sach-san-pham";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/product/insertProduct";
    }
    @RequestMapping(value = "sua-san-pham", method = RequestMethod.GET)
    public String editProductForm(@RequestParam(name = "id", required = false)Long id, Model model, RedirectAttributes redirectAttributes){
        if (id == null || id == 0){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
        }
        InsertProductModel products = productRepository.findProductById(id);
        model.addAttribute("insertProductModel", products);
        List<Categories> listCategories = categoriesRepository.getAllCategories();
        model.addAttribute("listCate", listCategories);
        String listJson = attributeRepository.getListAttributeSetAttributeViews();
        model.addAttribute("listJson", listJson);
        return "admin/product/editProduct";
    }
    @RequestMapping(value = "sua-san-pham", method = RequestMethod.POST)
    public String editProductSubmit(@Valid InsertProductModel productModel, BindingResult result, Model model){
        if (result.hasErrors()){
            List<Categories> listCategories = categoriesRepository.getAllCategories();
            model.addAttribute("listCate", listCategories);
            String listJson = attributeRepository.getListAttributeSetAttributeViews();
            model.addAttribute("listJson", listJson);
            return "admin/product/editProduct";
        }
        Boolean bl = productRepository.editProduct(productModel);
        if (bl){
            return "redirect:/quan-tri/san-pham/danh-sach-san-pham";
        }

        return "admin/product/editProduct";
    }
    @RequestMapping(value = "xoa-san-pham", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam (name = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/san-pham/danh-sach-san-pham";
        }
        Boolean bl = productRepository.deleteProduct(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Sản Phẩm Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/san-pham/danh-sach-san-pham";
        }
        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/san-pham/danh-sach-san-pham";
    }
}
