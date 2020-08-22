package com.project.sem4.controller;

import com.project.sem4.model.AttributeSet;
import com.project.sem4.model.Banner;
import com.project.sem4.model.Categories;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.BannerRepositoryImpl;
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
@RequestMapping("quan-tri/banner")
public class BannerController {
    @Autowired
    BannerRepositoryImpl repository;
    @RequestMapping(value = "danh-sach-banner", method = RequestMethod.GET)
    public String getAllBanner(Model model,@ModelAttribute("msg") String message){
        List<Banner> list = repository.getAllBanner();
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
        return "admin/banner/listBanner";
    }
    @RequestMapping(value = "them-banner", method = RequestMethod.GET)
    public String getFormAddCate(Model model){
        Banner banner = new Banner();
        model.addAttribute("banner", banner);
        return "admin/banner/insertBanner";
    }
    @RequestMapping(value = "them-banner", method = RequestMethod.POST)
    public String submitFormAddCate(@Valid Banner banner, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "admin/banner/insertBanner";
        }
        Boolean bl = repository.insertBanner(banner);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Banner Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/banner/danh-sach-banner";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/banner/insertBanner";
    }
    @RequestMapping(value = "sua-banner", method = RequestMethod.GET)
    public String updateAttributeSet(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/banner/danh-sach-banner";
        }
        Banner banner = repository.findBannerById(id);
        model.addAttribute("banner", banner);
        return "admin/banner/editBanner";
    }
    @RequestMapping(value = "sua-banner", method = RequestMethod.POST)
    public String saveUpdateAttributeSet(@Valid Banner banner, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "admin/banner/editBanner";
        }
        Boolean bl = repository.updateBanner(banner);
        if (bl){
            String msg = "success,Thông Báo,Sửa Banner Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/banner/danh-sach-banner";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Banner,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "admin/banner/editBanner";
    }
    @RequestMapping(value = "xoa-banner", method = RequestMethod.POST)
    public String deleteAttributeSet(@RequestParam (name = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/banner/danh-sach-banner";
        }
        Boolean bl = repository.deleteBanner(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa banner Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/banner/danh-sach-banner";
        }
//        AddressCities cities = repository.findCitiesById(id);
//        String city = cities.getId()+","+cities.getName()+","+cities.getType();
//        redirectAttributes.addAttribute("city", city);
        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/banner/danh-sach-banner";
    }
}
