package com.codegym.casestudy.model.contact_email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "guess_emails")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GuessEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email_address", columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    public GuessEmail(String email) {
        this.email = email;
    }
}
