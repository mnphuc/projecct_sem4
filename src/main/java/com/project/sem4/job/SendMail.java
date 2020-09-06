package com.project.sem4.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendMail {
    @Autowired
    private JavaMailSender javaMailSender;
    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("phucmnp@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
}
