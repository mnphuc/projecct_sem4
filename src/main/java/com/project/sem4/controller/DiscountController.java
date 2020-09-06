package com.project.sem4.controller;

import com.project.sem4.model.Banner;
import com.project.sem4.model.Discount;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.DiscountRepositoryImpl;
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
@RequestMapping("quan-tri/discount")
public class DiscountController {
    @Autowired
    DiscountRepositoryImpl discountRepository;
    @RequestMapping(value = "ma-giam-gia", method = RequestMethod.GET)
    public String listDiscount(Model  model,@ModelAttribute("msg") String message){
        List<Discount> list = discountRepository.getAllDiscount();
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
        return "admin/discount/listDiscount";
    }
    @RequestMapping(value = "them-ma-giam-gia", method = RequestMethod.GET)
    public String insertDiscount(Model model){
        Discount discount = new Discount();
        model.addAttribute("discount", discount);
        return "admin/discount/insertDiscount";
    }
    @RequestMapping(value = "them-ma-giam-gia", method = RequestMethod.POST)
    public String saveDiscount(@Valid Discount discount, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "admin/discount/insertDiscount";
        }
        Boolean bl = discountRepository.insertDiscount(discount);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Discount Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/discount/ma-giam-gia";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/discount/insertDiscount";
    }
    @RequestMapping(value = "sua-ma-giam-gia", method = RequestMethod.GET)
    public String updateAttributeSet(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/discount/ma-giam-gia";
        }
        Discount discount = discountRepository.findDiscountById(id);
        model.addAttribute("discount", discount);
        return "admin/discount/editDiscount";
    }
    @RequestMapping(value = "sua-ma-giam-gia", method = RequestMethod.POST)
    public String saveUpdateAttributeSet(@Valid Discount discount, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "admin/discount/editDiscount";
        }
        Boolean bl = discountRepository.editDiscount(discount);
        if (bl){
            String msg = "success,Thông Báo,Sửa Discount Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/discount/ma-giam-gia";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa discount,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "admin/discount/editDiscount";
    }
    @RequestMapping(value = "xoa-discount", method = RequestMethod.POST)
    public String deleteAttributeSet(@RequestParam (name = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/discount/ma-giam-gia";
        }
        Boolean bl = discountRepository.deleteDiscount(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa discount Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/discount/ma-giam-gia";
        }
        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/discount/ma-giam-gia";
    }
}
