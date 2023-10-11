package com.codegym.casestudy.service.contact_email.impl;

import com.codegym.casestudy.model.contact_email.GuessEmail;
import com.codegym.casestudy.repository.contact_email.IGuessEmailRepository;
import com.codegym.casestudy.service.contact_email.IGuessEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;

@Service
public class GuessEmailService implements IGuessEmailService {
    @Autowired
    private IGuessEmailRepository guessEmailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    @Override
    public void add(GuessEmail guessEmail) {
        try {
            this.guessEmailRepository.save(guessEmail);
        } catch (Exception e) {
            System.out.println("Error while adding new guess email: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        GuessEmail existedEmail = null;
        try {
            existedEmail = this.guessEmailRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (existedEmail != null) {
            try {
                this.guessEmailRepository.delete(existedEmail);
            } catch (Exception e) {
                System.out.println("Error while removing email: " + e.getMessage());
            }
        }
    }

    @Override
    public GuessEmail findById(int id) {
        GuessEmail existedEmail = null;
        try {
            existedEmail = this.guessEmailRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException());
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return existedEmail;
    }

    @Override
    public boolean findByEmail(String emailAddress) {
        GuessEmail existedEmail = null;
        try {
            existedEmail = this.guessEmailRepository.findGuessEmailByEmail(emailAddress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return existedEmail != null;
    }
    public void sendEmail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendResponse(String guessEmail) {
        System.out.println("-----------------------------------main controller" + guessEmail);

        String subject = "Trung tâm dạy lái xe Đà Nẵng Drift";
        String body =
                "<fieldset style=\"border: 2px solid #17a2b8 ;color: #00394f\">\n" +
                        "    <div>\n" +
                        "        <legend style=\"text-align: center\">\n" +
                        "            <h1 style=\"color: #00394f\">Đà Nẵng Drift</h1>\n" +
                        "        </legend>\n" +
                        "        </br>\n" +
                        "        <legend>\n" +
                        "            <h4>Xin chào: </h4>\n" + guessEmail +
                        "        </legend>\n" +
                        "        </br>\n" +
                        "    </div>\n" +
                        "    <div>\n" +
                        "        <p>Đây là thùng thư tự động của Đà Nẵng Drift</p>\n" +
                        "        <span>\n" +
                        "            Chúng tôi rẩt vui được tiếp nhận thông tin của bạn.\n" +
                        "            Mọi thắc mắc vui lòng liên hệ tới số điện thoại: +123 456 789\n" +
                        "            để được giải đáp.\n" +
                        "        <small>Vui lòng không trả lời thư này!</small>\n" +
                        "        </span>\n" +
                        "        <p>Chân thành cảm ơn</p>\n" +
                        "        <p>Đà Nẵng Drift</p>\n" +
                        "    </div>\n" +
                        "</fieldset>";
        sendEmail(guessEmail, subject, body);
    }
}
