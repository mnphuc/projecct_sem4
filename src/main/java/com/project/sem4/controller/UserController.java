package com.project.sem4.controller;

import com.project.sem4.model.*;
import com.project.sem4.model.view.InsertUser;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.UserRepositoryImpl;
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
@RequestMapping("quan-tri/nguoi-dung")
public class UserController {
    @Autowired
    UserRepositoryImpl userRepository;

    @RequestMapping(value = "danh-sach-nguoi-dung", method = RequestMethod.GET)
    public String getAllUser(Model model, @ModelAttribute("msg") String message){
        List<Users> list = userRepository.getAllUser();
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
        return "admin/user/listUser";
    }


    @RequestMapping(value = "them-nguoi-dung", method = RequestMethod.GET)
    public String insertDiscount(Model model){
        InsertUser users = new InsertUser();
        model.addAttribute("insertUser", users);
        List<Role> roleList = userRepository.getAllRoles();
        model.addAttribute("roleList", roleList);
        return "admin/user/insertUser";
    }
    @RequestMapping(value = "them-nguoi-dung", method = RequestMethod.POST)
    public String submitFormAddCate(@Valid InsertUser insertUser, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "admin/user/insertUser";
        }
        Boolean bl = userRepository.insertUser(insertUser);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Người Dùng Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/nguoi-dung/danh-sach-nguoi-dung";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/user/insertUser";
    }
//
//    @RequestMapping(value = "sua-nguoi-dung", method = RequestMethod.GET)
//    public String updateBlogCategory(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
//        if (id == null ){
//            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
//            redirectAttributes.addAttribute("msg", msg);
//            return "redirect:/quan-tri/blog/danh-muc-blog";
//        }
//        BlogCategories blogCategories = blogRepository.findBlogCategoryById(id);
//        model.addAttribute("blogCategories", blogCategories);
//        return "admin/blog/editBlogCategory";
//    }
//    @RequestMapping(value = "sua-danh-muc-blog", method = RequestMethod.POST)
//    public String saveBlogCategoryForm(@Valid BlogCategories blogCategories, BindingResult result, Model model, RedirectAttributes redirectAttributes){
//        if (result.hasErrors()) {
//            return "admin/blog/editBlogCategory";
//        }
//        Boolean bl = blogRepository.editBlogCategory(blogCategories);
//        if (bl){
//            String msg = "success,Thông Báo,Sửa Danh Mục Blog Thành Công,hide";
//            redirectAttributes.addFlashAttribute("msg", msg);
//            return "redirect:/quan-tri/blog/danh-muc-blog";
//        }
//        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Danh Mục Blog,hide";
//        redirectAttributes.addFlashAttribute("msg", msg);
//        return "admin/blog/editBlogCategory";
//    }
//    @RequestMapping(value = "xoa-danh-muc-blog", method = RequestMethod.POST)
//    public String deleteAttributeSet(@RequestParam (name = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
//        if (id == null ){
//            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
//            redirectAttributes.addFlashAttribute("msg", msg);
//            return "redirect:/quan-tri/blog/danh-muc-blog";
//        }
//        Boolean bl = blogRepository.deleteBlogCategory(id);
//        if (bl){
//            String msg = "success,Thông Báo,Xóa Danh Mục Blog Thành Công,hide";
//            redirectAttributes.addFlashAttribute("msg", msg);
//            return "redirect:/quan-tri/blog/danh-muc-blog";
//        }
//        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
//        redirectAttributes.addFlashAttribute("msg", msg);
//        return "redirect:/quan-tri/blog/danh-muc-blog";
//    }
}
