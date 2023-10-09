package com.codegym.casestudy.model.contact_email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contact_emails")
@Setter
@Getter
@NoArgsConstructor
public class ContactEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "contact_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String contactName;

    @Column(name = "email_address", nullable = false, columnDefinition = "VARCHAR(255)")
    private String emailAddress;

    @Column(name = "email_subject", nullable = false, columnDefinition = "VARCHAR(255)")
    private String emailSubject;

    @Column(name = "email_content", nullable = false, columnDefinition = "LONGTEXT")
    private String emailContent;

    public ContactEmail(String contactName, String emailAddress, String emailSubject, String emailContent) {
        this.contactName = contactName;
        this.emailAddress = emailAddress;
        this.emailSubject = emailSubject;
        this.emailContent = emailContent;
    }
}
