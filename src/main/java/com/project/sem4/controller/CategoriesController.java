package com.project.sem4.controller;

import com.project.sem4.model.AddressCities;
import com.project.sem4.model.Categories;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.CategoriesRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("quan-tri/danh-muc")
public class CategoriesController {
    @Autowired
    CategoriesRepositoryImpl categoriesRepository;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String  getAllCategories(Model model, @ModelAttribute("msg") String message, @ModelAttribute("cate") String cate){
        List<Categories> categories = categoriesRepository.getAllCategories();
        model.addAttribute("list", categories);
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
        if (!cate.isEmpty()){
            AddressCities cities1 = new AddressCities();
            String[] arr = cate.split(",");
            cities1.setId(Integer.parseInt(arr[0]));
            cities1.setName(arr[1]);
            cities1.setType(arr[2]);
            model.addAttribute("cities", cities1);
        }
        return "admin/categories/listCate";
    }

    @RequestMapping(value = "them-danh-muc", method = RequestMethod.GET)
    public String getFormAddCate(Model model){
        Categories categories = new Categories();
        model.addAttribute("categories", categories);
        List<Categories> listCate = categoriesRepository.getAllCategories();
        model.addAttribute("listCate", listCate);
        return "admin/categories/insertCategories";
    }
    @RequestMapping(value = "them-danh-muc", method = RequestMethod.POST)
    public String submitFormAddCate(@Valid Categories categories, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if (result.hasErrors()){
            List<Categories> listCate = categoriesRepository.getAllCategories();
            model.addAttribute("listCate", listCate);
            return "admin/categories/insertCategories";
        }
        Boolean bl = categoriesRepository.insertCategories(categories);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Danh Mục Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/danh-muc";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/categories/insertCategories";
    }

    @RequestMapping(value = "sua-danh-muc", method = RequestMethod.GET)
    public String getFormUpdateCategories(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/danh-muc";
        }
        Categories categories = categoriesRepository.findCategoryById(id);
        model.addAttribute("categories", categories);
        List<Categories> listCate = categoriesRepository.getAllCategories();
        model.addAttribute("listCate", listCate);
        return "admin/categories/editCategories";
    }
    @RequestMapping(value = "sua-danh-muc", method = RequestMethod.POST)
    public String formUpdateCategorySubmit(@Valid Categories categories, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            List<Categories> listCate = categoriesRepository.getAllCategories();
            model.addAttribute("listCate", listCate);
            return "admin/categories/editCategories";
        }
        Boolean bl = categoriesRepository.updateCategories(categories);
        if (bl){
            String msg = "success,Thông Báo,Sửa Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/danh-muc";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Danh Mục,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "admin/categories/editCategories";
    }
    @RequestMapping(value = "xoa-danh-muc", method = RequestMethod.POST)
    public String deleteCategories(@RequestParam (name = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/danh-muc";
        }
        Boolean bl = categoriesRepository.deleteCategories(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/danh-muc";
        }
//        AddressCities cities = repository.findCitiesById(id);
//        String city = cities.getId()+","+cities.getName()+","+cities.getType();
//        redirectAttributes.addAttribute("city", city);
        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/danh-muc";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String redirectIndex(){
        return "redirect:/quan-tri/danh-muc";
    }
}
