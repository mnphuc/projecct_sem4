package com.project.sem4.controller;

import com.project.sem4.model.AddressCities;
import com.project.sem4.model.AddressDistrict;
import com.project.sem4.model.AddressWards;
import com.project.sem4.model.Employee;
import com.project.sem4.model.view.AddressDistrictView;
import com.project.sem4.model.view.AddressWardView;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.AddressRepositoryImpl;
import com.project.sem4.service.AddressCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("quan-tri/address")
public class AddressController {
    @Autowired
    AddressCitiesService service;
    @Autowired
    private AddressRepositoryImpl repository;
    @RequestMapping(value = "danh-sach-tinh-vietnam", method = RequestMethod.GET)
    public String getAllCity(ModelMap model, @ModelAttribute("msg") String message, @ModelAttribute("city") String city ){
        List<AddressCities> list =  repository.getAllCity();
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
        if (!city.isEmpty()){
            AddressCities cities1 = new AddressCities();
            String[] arr = city.split(",");
            cities1.setId(Integer.parseInt(arr[0]));
            cities1.setName(arr[1]);
            cities1.setType(arr[2]);
            model.addAttribute("cities", cities1);
        }
        return "admin/address/listCities";
    }
    @RequestMapping(value = "/them-tinh-tp", method = RequestMethod.GET)
    public String formCities(Model model){
        AddressCities citie  = new AddressCities();
        model.addAttribute("addressCities", citie);
        return "admin/address/insertCities";
    }

    @RequestMapping(value = "/them-tinh-tp", method = RequestMethod.POST)
    public String saveCity(@Valid AddressCities cities, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        List<Message> list = new ArrayList<>();
        if (result.hasErrors()) {
            return "admin/address/insertCities";
        }
        Boolean bl = service.insertCity(cities);
        if (bl) {
            String msg = "success,Thông báo,Thêm Mới Thành Công,hide";
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-tinh-vietnam";
        }
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("ID này đã tồn tại");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/address/insertCities";
    }
    @RequestMapping(value = "sua-tinh-tp", method = RequestMethod.GET)
    public String formEditCities(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
         if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình";
             redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-tinh-vietnam";
        }
         AddressCities cities = repository.findCitiesById(id);
         model.addAttribute("addressCities", cities);
        return "admin/address/editCities";
    }
    @RequestMapping(value = "sua-tinh-tp", method = RequestMethod.POST)
    public String formEditSubmit(@Valid AddressCities cities, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            return "admin/address/editCities";
        }
        Boolean bl = repository.editCities(cities);
        if (bl){
            String msg = "success,Thông Báo,Sửa Thành Công,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-tinh-vietnam";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Tỉnh/TP,hide";
        redirectAttributes.addAttribute("msg", msg);
        return "admin/address/editCities";
    }
    @RequestMapping(value = "/xoa-cities", method = RequestMethod.POST)
    public String deleteCities(@RequestParam (name = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-tinh-vietnam";
        }
        Boolean bl = service.deleteCities(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Thành Công,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-tinh-vietnam";
        }
        AddressCities cities = repository.findCitiesById(id);
        String city = cities.getId()+","+cities.getName()+","+cities.getType();
        redirectAttributes.addAttribute("city", city);
        String msg = "error,Lỗi,Xóa Không Thành Công,delete";
        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/quan-tri/address/danh-sach-tinh-vietnam";
    }
    @RequestMapping(value = "danh-sach-quan-huyen", method = RequestMethod.GET)
    public String getAllDistrict(Model model, @ModelAttribute("msg") String message, @ModelAttribute("district") String district){
        List<AddressDistrictView> list = repository.getAllDistrict();
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
        if (!district.isEmpty()){
            AddressCities cities1 = new AddressCities();
            String[] arr = district.split(",");
            cities1.setId(Integer.parseInt(arr[0]));
            cities1.setName(arr[1]);
            cities1.setType(arr[2]);
            model.addAttribute("cities", cities1);
        }
        return "admin/address/listDistrict";
    }

    @RequestMapping(value = "them-quan-huyen", method = RequestMethod.GET)
    public String addDistrict(Model model){
        AddressDistrict district = new AddressDistrict();
        List<AddressCities> list = repository.getAllCity();
        model.addAttribute("listCities", list);
        model.addAttribute("addressDistrict", district);
        return "admin/address/insertDistrict";
    }
    @RequestMapping(value = "them-quan-huyen", method = RequestMethod.POST)
    public String addDistrictSubmit(@Valid AddressDistrict district, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        List<Message> list = new ArrayList<>();
        if (result.hasErrors()){
            List<AddressCities> lists = repository.getAllCity();
            model.addAttribute("listCities", lists);
            return "admin/address/insertDistrict";
        }
        Boolean bl = service.insertDistrictService(district);
        if (bl){
            String msg = "success,Thông báo,Thêm Mới Thành Công,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-quan-huyen";
        }
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("ID này đã tồn tại");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/address/insertDistrict";
    }

    @RequestMapping(value = "sua-quan-huyen", method = RequestMethod.GET)
    public String getFormEditDistrict(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-quan-huyen";
        }
        List<AddressCities> cities = repository.getAllCity();
        AddressDistrictView district  = repository.findDistrictById(id);
        model.addAttribute("listCities", cities);
        model.addAttribute("addressDistrict", district);
        return "admin/address/editDistrict";
    }

    @RequestMapping(value = "sua-quan-huyen", method = RequestMethod.POST)
    public String formEditDistrictSubmit(@Valid AddressDistrict district, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        List<AddressCities> cities = repository.getAllCity();
        if (result.hasErrors()){
            model.addAttribute("listCities", cities);
            return "admin/address/editDistrict";
        }
        Boolean bl = repository.editDistrict(district);
        if (bl){
            String msg = "success,Thông Báo,Sửa Thành Công,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-quan-huyen";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Quận/Huyện,hide";
        redirectAttributes.addAttribute("msg", msg);
        return "admin/address/editDistrict";
    }

    @RequestMapping(value = "xoa-quan-huyen", method = RequestMethod.POST)
    public String deleteDistrict(@RequestParam(value = "id", required = false) Integer id, Model model, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-quan-huyen";
        }
        Boolean bl = service.deleteDistrict(id);
        if (bl){
            String msg = "success,Thông Báo,Xóa Thành Công,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-quan-huyen";
        }
        AddressDistrictView district = repository.findDistrictById(id);
        String districts = district.getId()+","+district.getName()+","+district.getType();
        redirectAttributes.addAttribute("district", districts);
        String msg = "error,Lỗi,Xóa Không Thành Công,delete";
        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/quan-tri/address/danh-sach-quan-huyen";
    }

    @RequestMapping(value = "danh-sach-xa-phuong", method = RequestMethod.GET)
    public String getAllWards(Model model, @ModelAttribute("msg") String message){
        List<AddressWardView> list = repository.getAllWards();
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
        return "admin/address/listWards";
    }

    @RequestMapping(value = "them-xa-phuong", method = RequestMethod.GET)
    public String insertWardForm(Model model){
        List<AddressDistrictView> districtViews = repository.getAllDistrict();
        model.addAttribute("listDistrict", districtViews);
        AddressWards wards = new AddressWards();
        model.addAttribute("addressWards", wards);
        return "admin/address/insertWards";
    }

    @RequestMapping(value = "them-xa-phuong", method = RequestMethod.POST)
    public String insertWardSubmit(@Valid AddressWards wards, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            List<AddressDistrictView> districtViews = repository.getAllDistrict();
            model.addAttribute("listDistrict", districtViews);
            return "admin/address/insertWards";
        }
        Boolean bl = service.insertWards(wards);
        if (bl){
            String msg = "success,Thông báo,Thêm Mới Thành Công,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-xa-phuong";
        }
        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setKey("error");
        message.setType("Lỗi thêm mới");
        message.setValue("ID này đã tồn tại");
        list.add(message);
        model.addAttribute("msg", list);
        return "admin/address/insertDistrict";
    }
    @RequestMapping(value = "sua-xa-phuong", method = RequestMethod.GET)
    public String editWardsForm(Model model, @RequestParam(name = "id", required = false) Integer id, RedirectAttributes redirectAttributes){
        if (id == null ){
            String msg = "warning,Cảnh Báo,Bạn Đang Cố Tấn Công Hệ Thống Của Mình,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-xa-phuong";
        }
        List<AddressWardView> allWards = repository.getAllWards();
        AddressWardView wardView  = repository.findWardById(id);
        model.addAttribute("listDistrict", allWards);
        model.addAttribute("addressWards", wardView);
        return "admin/address/editWard";
    }

    @RequestMapping(value = "sua-xa-phuong", method = RequestMethod.POST)
    public String editWardSubmit(@Valid AddressWards wards, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        List<AddressDistrictView> district1 = repository.getAllDistrict();
        if (result.hasErrors()){
            model.addAttribute("listDistrict", district1);
            return "admin/address/editWard";
        }
        Boolean bl = repository.updateWards(wards);
        if (bl){
            String msg = "success,Thông Báo,Sửa Thành Công,hide";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/quan-tri/address/danh-sach-xa-phuong";
        }
        String msg = "error,Thất Bại,Có Lỗi Khi Sửa Quận/Huyện,hide";
        redirectAttributes.addAttribute("msg", msg);
        return "admin/address/editWard";
    }
}
