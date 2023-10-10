package com.codegym.casestudy.repository.contact_email;

import com.codegym.casestudy.model.contact_email.ContactEmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface IContactEmailRepository extends JpaRepository<ContactEmail, Integer> {
    @Query(value = " select id , contact_name ," +
            "  email_address , email_subject , " +
            "  email_content " +
            " from contact_emails ", nativeQuery = true)
    Page<ContactEmail> findContactEmailBy(Pageable pageable);

    @Query(value = " select email_address from contact_emails " +
            "group by email_address", nativeQuery = true)
    List<String> findContactEmailsByEmailAddress();
}
