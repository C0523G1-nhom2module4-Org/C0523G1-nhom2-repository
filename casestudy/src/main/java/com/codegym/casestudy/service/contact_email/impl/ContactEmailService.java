package com.codegym.casestudy.service.contact_email.impl;

import com.codegym.casestudy.model.contact_email.ContactEmail;
import com.codegym.casestudy.repository.contact_email.IContactEmailRepository;
import com.codegym.casestudy.service.contact_email.IContactEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class ContactEmailService implements IContactEmailService {
    @Autowired
    private IContactEmailRepository contactEmailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    @Override
    public void add(ContactEmail contactEmail) {
        try {
            this.contactEmailRepository.save(contactEmail);
        } catch (Exception e) {
            System.out.println("Error while adding new contact email: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        ContactEmail contactEmail = null;
        try {
            contactEmail = this.contactEmailRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException());
            this.contactEmailRepository.delete(contactEmail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ContactEmail findById(int id) {
        return this.contactEmailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Page<ContactEmail> getGuessEmailList(Pageable pageable) {
        return this.contactEmailRepository.findContactEmailBy(pageable);
    }

    @Override
    public List<String> findContactEmailsByEmailAddress() {
        return this.contactEmailRepository.findContactEmailsByEmailAddress();
    }

    @Override
    public void sendAdsEmail(String subject, String body) {
        List<String> emails = findContactEmailsByEmailAddress();

        for (int i = 0; i < emails.size(); i++) {
            String email = emails.get(i);
            sendEmail(email, subject, body);
        }
    }

    private void sendEmail(String to, String subject, String body) {
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
}
