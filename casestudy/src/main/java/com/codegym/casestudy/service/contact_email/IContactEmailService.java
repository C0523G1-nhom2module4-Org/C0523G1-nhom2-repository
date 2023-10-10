package com.codegym.casestudy.service.contact_email;

import com.codegym.casestudy.model.contact_email.ContactEmail;

public interface IContactEmailService {
    void add(ContactEmail contactEmail);

    void delete(int id);

    ContactEmail findById(int id);
}
