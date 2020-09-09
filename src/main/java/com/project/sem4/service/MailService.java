package com.project.sem4.service;

import com.project.sem4.model.service.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    public ResponseEntity<?> sendMail(Mail mail) {
        send(mail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Async
    public void sendEmailToKen(SimpleMailMessage email) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        javaMailSender.send(mail);
    }
    public void send(Mail mail) {
        final Context context = new Context();
        context.setVariable("message", mail.getMessage());
        String body = templateEngine.process("mail", context);
        sendPreparedMail(mail.getEmail(), mail.getObject(), body, true);
    }

    private void sendPreparedMail(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
        } catch (Exception e) {
            LOGGER.error("Problem with sending email to: {}, error message: {}", to, e.getMessage());
        }
    }
}