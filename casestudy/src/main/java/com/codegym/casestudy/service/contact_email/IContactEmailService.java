package com.codegym.casestudy.service.contact_email;

import com.codegym.casestudy.model.contact_email.ContactEmail;
import com.codegym.casestudy.model.contact_email.GuessEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContactEmailService {
    void add(ContactEmail contactEmail);

    void delete(int id);

    ContactEmail findById(int id);

    Page<ContactEmail> getGuessEmailList(Pageable pageable);
    void sendAdsEmail(String subject, String body);

    List<String> findContactEmailsByEmailAddress();

}
