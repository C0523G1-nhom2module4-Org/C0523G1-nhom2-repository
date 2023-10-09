package com.codegym.casestudy.service.main_page_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
@Service
public class MainPageService implements IMainPageService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String to, String subject, String body) {
        MimeMessage email = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            javaMailSender.send(email);
        } catch (Exception e) {
            System.out.println("Error while sending email: " + e.getMessage());
        }
    }
}
