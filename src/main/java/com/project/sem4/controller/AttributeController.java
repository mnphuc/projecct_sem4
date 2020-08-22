package com.project.sem4.controller;

import com.project.sem4.model.AddressCities;
import com.project.sem4.model.Attribute;
import com.project.sem4.model.AttributeSet;
import com.project.sem4.model.Categories;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.AttributeRepositoryImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
@RequestMapping("quan-tri/thuoc-tinh")
public class AttributeController {
    @Autowired
    AttributeRepositoryImpl repository;
    @RequestMapping(value = "danh-sach-thuoc-tinh", method = RequestMethod.GET)
    public String getAllAttributeSet(Model model, @ModelAttribute("msg") String message, @ModelAttribute("attr") String attr){
        List<AttributeSet> attributeSets = repository.getAllAttributeSet();
        model.addAttribute("list", attributeSets);
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
        return "admin/attributes/listAttributeSet";
    }
    @RequestMapping(value = "them-thuoc-tinh", method = RequestMethod.GET)
    public String insertAttribute(Model model){
        AttributeSet attributeSet = new AttributeSet();
        model.addAttribute("attributeSet", attributeSet);
        return "admin/attributes/insertAttributeSet";
    }
    @RequestMapping(value = "them-thuoc-tinh", method = RequestMethod.POST)
    public String saveAttributeSet(@Valid AttributeSet attributeSet, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "admin/attributes/insertAttributeSet";
        }
        Boolean bl = repository.insertAttributeSet(attributeSet);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Thuộc Tính Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới thuộc tính");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/attributes/insertAttributeSet";
    }
    @RequestMapping(value = "sua-thuoc-tinh", method = RequestMethod.GET)
    public String updateAttributeSet(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
        AttributeSet attributeSet = repository.findAttributeSetById(id);
        model.addAttribute("attributeSet", attributeSet);
        return "admin/attributes/editAttributeSet";
    }
    @RequestMapping(value = "sua-thuoc-tinh", method = RequestMethod.POST)
    public String saveUpdateAttributeSet(@Valid AttributeSet attributeSet, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "admin/attributes/editAttributeSet";
        }
        Boolean bl = repository.updateAttributeSet(attributeSet);
        if (bl){
            String msg = "success,Thông Báo,Sửa Thuộc Tính Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Danh Mục,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "admin/attributes/editAttributeSet";
    }

    @RequestMapping(value = "xoa-thuoc-tinh", method = RequestMethod.POST)
    public String deleteAttributeSet(@RequestParam (name = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
        Boolean bl = repository.deleteAttributeSet(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Thuộc Tính Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
//        AddressCities cities = repository.findCitiesById(id);
//        String city = cities.getId()+","+cities.getName()+","+cities.getType();
//        redirectAttributes.addAttribute("city", city);
        String msg = "error,Lỗi,Xóa Không Thành Công,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
    }
    @RequestMapping(value = "them-gia-tri-thuoc-tinh", method = RequestMethod.GET)
    public String insertAttribute(Model model, @RequestParam (name = "id", required = false) Integer id){
        Attribute attribute = new Attribute();
        if (id != null){
            attribute.setAttributeSetId(id);
        }
        model.addAttribute("attribute", attribute);
        List<AttributeSet> list = repository.getAllAttributeSet();
        model.addAttribute("listAttrSet", list);
        return "admin/attributes/insertAttribute";
    }

    @RequestMapping(value = "them-gia-tri-thuoc-tinh", method = RequestMethod.POST)
    public String saveAttribute(@Valid Attribute attribute, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            List<AttributeSet> list = repository.getAllAttributeSet();
            model.addAttribute("listAttrSet", list);
            return "admin/attributes/insertAttribute";
        }
        Boolean bl = repository.createAttribute(attribute);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Giá Trị Thuộc Tính Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("Có Lỗi trong quá trình thêm mới giá trị thuộc tính");
        message.setCheck("hide");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/attributes/insertAttribute";
    }
    @RequestMapping(value = "sua-gia-tri-cua-thuoc-tinh", method = RequestMethod.GET)
    public String updateAttribute(Model model, @RequestParam (name = "id", required = false) Integer id){
        Attribute attribute = repository.findAttributeById(id);
        model.addAttribute("attribute", attribute);
        List<AttributeSet> list = repository.getAllAttributeSet();
        model.addAttribute("listAttrSet", list);
        return "admin/attributes/editAttribute";
    }
    @RequestMapping(value = "sua-gia-tri-cua-thuoc-tinh", method = RequestMethod.POST)
    public String updateAttributeSubmit(@Valid Attribute attribute, BindingResult result, Model model, RedirectAttributes redirectAttributes ){
        if (result.hasErrors()) {
            List<AttributeSet> list = repository.getAllAttributeSet();
            model.addAttribute("listAttrSet", list);
            return "admin/attributes/editAttribute";
        }
        Boolean bl = repository.updateAttribute(attribute);
        if (bl){
            String msg = "success,Thông Báo,Sửa Giá Trị Thuộc Tính Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Giá Trị Thuộc Tính,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "admin/attributes/editAttribute";
    }

    @RequestMapping(value = "xoa-gia-tri-thuoc-tinh", method = RequestMethod.POST)
    public String deleteAttribute(@RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
        Boolean bl = repository.deleteAttribute(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Giá Trị Thuộc Tính Thành Công,show";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
        }
//        AddressCities cities = repository.findCitiesById(id);
//        String city = cities.getId()+","+cities.getName()+","+cities.getType();
//        redirectAttributes.addAttribute("city", city);
        String msg = "error,Lỗi,Xóa Không Thành Công,show";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/quan-tri/thuoc-tinh/danh-sach-thuoc-tinh";
    }
}
