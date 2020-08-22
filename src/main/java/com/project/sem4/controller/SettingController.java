package com.project.sem4.controller;

import com.project.sem4.model.AttributeSet;
import com.project.sem4.model.Payment;
import com.project.sem4.model.Popup;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.PaymentRepositoryImpl;
import com.project.sem4.repository.interfaces.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("quan-tri/thiet-lap")
public class SettingController {
    @Autowired
    PaymentRepositoryImpl repository;
    @Autowired
    SettingRepository settingRepository;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){
        return "";
    }
    @RequestMapping(value = "thanh-toan", method = RequestMethod.GET)
    public String getAllPayment(Model model, @ModelAttribute("msg") String message){
        List<Payment> list = repository.getAllPayment();
        model.addAttribute("payment", list);
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
        return "admin/setting/payment";
    }
    @RequestMapping(value = "them-phuong-thuc-thanh-toan", method = RequestMethod.GET)
    public String insertPayment(@RequestParam(value = "id", required = false)Integer id, Model model){
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "admin/setting/insertPayment";
    }
    @RequestMapping(value = "them-phuong-thuc-thanh-toan", method = RequestMethod.POST)
    public String savePayment(@Valid Payment payment, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "admin/setting/insertPayment";
        }
        Boolean bl = repository.insertPayment(payment);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Phương Thức Thanh Toán Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thiet-lap/thanh-toan";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới phương thức thanh toán");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/setting/insertPayment";
    }
    @RequestMapping(value = "sua-phuong-thuc-thanh-toan", method = RequestMethod.GET)
    public String updatePaymentForm(@RequestParam(value = "id", required = false)Integer id, Model model){
        Payment payment = repository.findPaymentById(id);
        model.addAttribute("payment", payment);
        return "admin/setting/editPayment";
    }
    @RequestMapping(value = "sua-phuong-thuc-thanh-toan", method = RequestMethod.POST)
    public String updatePaymentSubmit(@Valid Payment payment, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "admin/setting/editPayment";
        }
        Boolean bl = repository.updatePayment(payment);
        if (bl){
            String msg = "success,Thông Báo,Sửa Phương Thức Thanh Toán Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thiet-lap/thanh-toan";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Phương thức thanh toán,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "admin/setting/editPayment";
    }

    @RequestMapping(value = "xoa-phuong-thuc-thanh-toan", method = RequestMethod.POST)
    public String deletePayment(@RequestParam(value = "id", required = false)Integer id, Model model, RedirectAttributes redirectAttributes){
        Boolean bl = repository.deletePayment(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Phương Thức Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thiet-lap/thanh-toan";
        }
        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/thiet-lap/thanh-toan";
    }
    @RequestMapping(value = "thong-bao", method = RequestMethod.GET)
    public String getAllPopup(Model model, @ModelAttribute("msg") String message){
        List<Popup> list = settingRepository.getAllPopup();
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
        return "admin/popup/listPopup";
    }
    @RequestMapping(value = "them-thong-bao", method = RequestMethod.GET)
    public String insertPopup(@RequestParam(value = "id", required = false)Integer id, Model model){
        Popup popup = new Popup();
        model.addAttribute("popup", popup);
        return "admin/popup/insertPopup";
    }
    @RequestMapping(value = "them-thong-bao", method = RequestMethod.POST)
    public String savePopup(@Valid Popup popup, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "admin/popup/insertPopup";
        }
        Boolean bl = settingRepository.insertPopup(popup);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Thông Báo Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thiet-lap/thong-bao";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới thông báo");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/popup/insertPopup";
    }
    @RequestMapping(value = "sua-thong-bao", method = RequestMethod.GET)
    public String updatePopup(@RequestParam(value = "id", required = false)Integer id, Model model){
        Popup popup = settingRepository.findPopupById(id);
        model.addAttribute("popup", popup);
        return "admin/popup/editPopup";
    }
    @RequestMapping(value = "sua-thong-bao", method = RequestMethod.POST)
    public String updatePopupSubmit(@Valid Popup popup, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "admin/popup/editPopup";
        }
        Boolean bl = settingRepository.updatePopup(popup);
        if (bl){
            String msg = "success,Thông Báo,Sửa Thông Báo Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thiet-lap/thong-bao";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Thông Báo,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "admin/setting/editPayment";
    }

    @RequestMapping(value = "xoa-thong-bao", method = RequestMethod.POST)
    public String deletePopup(@RequestParam(value = "id", required = false)Integer id, Model model, RedirectAttributes redirectAttributes){
        Boolean bl = settingRepository.deletePopup(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Phương Thức Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thiet-lap/thong-bao";
        }
        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/thiet-lap/thong-bao";
    }
}
