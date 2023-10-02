package com.codegym.casestudy.model.student;

import com.codegym.casestudy.dto.account.AccountDto;
import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.model.classes.Classes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String identity;
    private int gender;
    private String birthday ;
    private String phoneNumber;
    private int point;
    private String address;
    private int status;

    @ManyToOne
    @JoinColumn(name = "classes_id", referencedColumnName = "id")
    private Classes classes;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}
