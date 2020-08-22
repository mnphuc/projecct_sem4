package com.project.sem4.controller;

import com.project.sem4.model.Banner;
import com.project.sem4.model.BlogCategories;
import com.project.sem4.model.Blogs;
import com.project.sem4.model.Users;
import com.project.sem4.model.view.BlogView;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.BlogRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quan-tri/blog")
@EnableWebSecurity
public class BlogController {
    @Autowired
    BlogRepositoryImpl blogRepository;
    @RequestMapping(value = "danh-muc-blog", method = RequestMethod.GET)
    public String getAllCategoryBlog(Model model, @ModelAttribute("msg") String message){
        List<BlogCategories> list = blogRepository.getAllBlogCategory();
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
        return "admin/blog/listBlogCategory";
    }
    @RequestMapping(value = "them-danh-muc-blog", method = RequestMethod.GET)
    public String getFormAddCate(Model model){
        BlogCategories blogCategories = new BlogCategories();
        model.addAttribute("blogCategories", blogCategories);
        return "admin/blog/insertBlogCategory";
    }
    @RequestMapping(value = "them-danh-muc-blog", method = RequestMethod.POST)
    public String submitFormAddCate(@Valid BlogCategories blogCategories, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "admin/blog/insertBlogCategory";
        }
        Boolean bl = blogRepository.insertBlogCategory(blogCategories);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Danh Mục Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/blog/danh-muc-blog";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới danh mục");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/blog/insertBlogCategory";
    }
    @RequestMapping(value = "sua-danh-muc-blog", method = RequestMethod.GET)
    public String updateAttributeSet(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/blog/danh-muc-blog";
        }
        BlogCategories blogCategories = blogRepository.findBlogCategoryById(id);
        model.addAttribute("blogCategories", blogCategories);
        return "admin/blog/editBlogCategory";
    }
    @RequestMapping(value = "sua-danh-muc-blog", method = RequestMethod.POST)
    public String saveUpdateAttributeSet(@Valid BlogCategories blogCategories, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "admin/blog/editBlogCategory";
        }
        Boolean bl = blogRepository.editBlogCategory(blogCategories);
        if (bl){
            String msg = "success,Thông Báo,Sửa Danh Mục Blog Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/blog/danh-muc-blog";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Danh Mục Blog,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "admin/blog/editBlogCategory";
    }
    @RequestMapping(value = "xoa-danh-muc-blog", method = RequestMethod.POST)
    public String deleteAttributeSet(@RequestParam (name = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/blog/danh-muc-blog";
        }
        Boolean bl = blogRepository.deleteBlogCategory(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Danh Mục Blog Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/blog/danh-muc-blog";
        }
        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/blog/danh-muc-blog";
    }
    @RequestMapping(value = "danh-sach-bai-viet", method = RequestMethod.GET)
    public String getAllBlog(Model model, @ModelAttribute("msg") String message){
        List<BlogView> list = blogRepository.getAllBlog();
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
        return "admin/blog/listBlog";
    }
    @RequestMapping(value = "them-blog", method = RequestMethod.GET)
    public String getFormAddBlog(Model model){
        Blogs blogs = new Blogs();
        model.addAttribute("blogs", blogs);
        List<BlogCategories> blogCategories = blogRepository.getAllBlogCategory();
        model.addAttribute("blogCategories", blogCategories);
        return "admin/blog/insertBlog";
    }
    @RequestMapping(value = "them-blog", method = RequestMethod.POST)
    public String submitFormBlog(@Valid Blogs blogs, HttpServletRequest request, BindingResult result, Model model, RedirectAttributes redirectAttributes){
//        Authentication authentication = null;
//        Users users = (Users) authentication.getPrincipal();
//        Long userId = users.getUserID();
        Long userId = (Long) request.getSession().getAttribute("userId");
        Long uId = userId;
        blogs.setUserId(uId);
        if (result.hasErrors()){
            List<BlogCategories> blogCategories = blogRepository.getAllBlogCategory();
            model.addAttribute("blogCategories", blogCategories);
            return "admin/blog/insertBlog";
        }

        Boolean bl = blogRepository.insertBlog(blogs);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Bài Viết Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/blog/danh-sach-bai-viet";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới Bài Viết");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/blog/insertBlog";
    }

}
