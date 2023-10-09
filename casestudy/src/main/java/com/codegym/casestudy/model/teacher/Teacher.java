package com.codegym.casestudy.model.teacher;

import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.model.assignment.Assignment;
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
    @Column(name = "identity")
    private String identity;
    @Column(name = "gender")
    private int gender;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "salary")
    private int salary;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}
