package com.codegym.casestudy.model.student;

import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.model.classes.Classes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "student_name")
    private String name;
    @Column(name = "student_identity")
    private String identity;
    @Column(name = "student_gender")
    private int gender;
    @Column(name = "student_birthday")
    private String birthday ;
    @Column(name = "student_phone_number")
    private String phoneNumber;
    @Column(name = "student_point")
    private int point;
    @Column(name = "student_address")
    private String address;
    @Column(name = "student_status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "classes_id", referencedColumnName = "id")
    private Classes classes;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}
