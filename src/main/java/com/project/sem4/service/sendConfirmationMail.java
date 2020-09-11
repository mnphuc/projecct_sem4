package com.project.sem4.service;

import com.project.sem4.model.service.Mail;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class sendConfirmationMail {
    @Autowired
    private JavaMailSender javaMailSender;

    public Job sendConfirmationMail(String userMail, String token) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("phucmnp@gmail.com");
        msg.setSubject("Kích Hoạt Tài Khoản");
        msg.setText("Click vào link này http://localhost:8080/dang-ky/confirm?token="+token +" để kích hoạt tài khoản");
        javaMailSender.send(msg);
        return Object::notify;
    }
    public Job sendResetPasswordMail(String userMail, String token) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("phucmnp@gmail.com");
        msg.setSubject("Khôi Phục Tải Khoản");
        msg.setText("Click vào link này http://localhost:8080/reset/nhap-mat-khau?token="+token +" để lấy lại mật khẩu");
        javaMailSender.send(msg);
        return Object::notify;
    }
}
