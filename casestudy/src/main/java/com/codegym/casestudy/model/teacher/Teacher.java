package com.codegym.casestudy.model.teacher;

import com.codegym.casestudy.model.account.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "teacher_name")
    private String name;
    @Column(name = "teacher_identity")
    private String identity;
    @Column(name = "teacher_gender")
    private int gender;
    @Column(name = "teacher_birthday")
    private String birthday;
    @Column(name = "teacher_phone_number")
    private String phoneNumber;
    @Column(name = "teacher_address")
    private String address;
    @Column(name = "teacher_salary")
    private int salary;
    @Column(name = "teacher_status")
    private int status;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
