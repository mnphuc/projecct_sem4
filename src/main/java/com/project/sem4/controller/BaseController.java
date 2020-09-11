package com.project.sem4.controller;

import com.project.sem4.model.Users;
import com.project.sem4.model.service.ConfirmationToken;
import com.project.sem4.model.view.InsertUser;
import com.project.sem4.model.view.Message;
import com.project.sem4.repository.ConfirmationTokenRepositoryImpl;
import com.project.sem4.repository.UserRepositoryImpl;
import com.project.sem4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
public class BaseController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepositoryImpl userRepository;
    @Autowired
    ConfirmationTokenRepositoryImpl confirmationTokenRepository;
    @RequestMapping("/dang-nhap-quan-tri")
    public String login1(@RequestParam(required = false) String message, final Model model) {
        if (message != null && !message.isEmpty()) {
            if (message.equals("logout")) {
                model.addAttribute("message", "Logout!");
            }
            if (message.equals("error")) {
                model.addAttribute("message", "Login Failed!");
            }
        }
        return "admin/login";
    }

    @RequestMapping("/dang-nhap")
    public String login2(@RequestParam(required = false) String message, @ModelAttribute("msg") String msg, final Model model) {
        if (message != null && !message.isEmpty()) {
            if (message.equals("logout")) {
                model.addAttribute("message", "Logout!");
            }
            if (message.equals("error")) {
                List<Message> list = new ArrayList<>();
                Message message1 = new Message();
                message1.setKey("error");
                message1.setType("Đăng Nhập Không Thàng Công");
                message1.setValue("Tài Khoản Mật Khẩu Không Chính Xác");
                message1.setCheck("hide");
                list.add(message1);
                model.addAttribute("msg", list);
                    model.addAttribute("err", "Sai Tài Khoản Mật Khẩu");
            }
        }
        if (!msg.isEmpty()){
            List<Message> list1 = new ArrayList<>();
            String[] arr = msg.split(",");
            Message message1 = new Message();
            message1.setKey(arr[0]);
            message1.setType(arr[1]);
            message1.setValue(arr[2]);
            message1.setCheck(arr[3]);
            list1.add(message1);
            model.addAttribute("msg", list1);
        }
        InsertUser insertUser = new InsertUser();
        model.addAttribute("insertUser", insertUser);
        return "login";
    }
    @RequestMapping(value = "dang-ky", method = RequestMethod.POST)
    public String signUp(@Valid InsertUser insertUser, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "login";
        }
        Users users = userRepository.getUserByEmail(insertUser.getEmail());
        if (users.getUserID() == null){
            Boolean bl = userService.signUpUser(insertUser);
            if (bl){
                return "redirect:/dang-nhap";
            }
        }
        return "login";
    }
    @RequestMapping(value = "dang-ky/confirm", method = RequestMethod.GET)
    public String confirmUser(@RequestParam(value = "token")String token, RedirectAttributes redirectAttributes){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findTokenByToken(token);
        if (confirmationToken.getId() != null){
            Boolean bll = userRepository.confirmUsers(confirmationToken.getUserId(), true);
            Boolean bl = confirmationTokenRepository.deleteToken(confirmationToken.getId());
            if (bl){
                String msg = "success,Thông báo,Kích Hoạt Thành Công,hide";
                redirectAttributes.addFlashAttribute("msg", msg);
                return "redirect:/dang-nhap";
            }
        }
        String msg = "error,Thông báo,Kích Hoạt Thất Bại,hide";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/dang-nhap";
    }
}
