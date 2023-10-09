package com.codegym.casestudy.repository.contact_email;

import com.codegym.casestudy.model.contact_email.ContactEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IContactEmailRepository extends JpaRepository<ContactEmail, Integer> {
}
