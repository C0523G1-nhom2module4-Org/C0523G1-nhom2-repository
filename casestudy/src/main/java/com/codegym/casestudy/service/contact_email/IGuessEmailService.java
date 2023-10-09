package com.codegym.casestudy.service.contact_email;

import com.codegym.casestudy.model.contact_email.GuessEmail;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public interface IGuessEmailService {
    void add(GuessEmail guessEmail);

    void delete(int id);

    GuessEmail findById(int id);

    boolean findByEmail(String emailAddress);

    void sendResponse(String guessEmail);

    void sendEmail(String to, String subject, String body);
}
