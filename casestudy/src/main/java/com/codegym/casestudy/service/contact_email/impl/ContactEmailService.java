package com.codegym.casestudy.service.contact_email.impl;

import com.codegym.casestudy.model.contact_email.ContactEmail;
import com.codegym.casestudy.repository.contact_email.IContactEmailRepository;
import com.codegym.casestudy.service.contact_email.IContactEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactEmailService implements IContactEmailService {
    @Autowired
    private IContactEmailRepository contactEmailRepository;
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
}
