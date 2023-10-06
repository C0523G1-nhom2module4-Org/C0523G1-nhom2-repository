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
    @Column(name = "identity")
    private String identity;
    @Column(name = "gender")
    private int gender;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "phone")
    private String phone;
    @Column(name = "graduate_point")
    private int graduatePoint;
    @Column(name = "address")
    private String address;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "classes_id", referencedColumnName = "id")
    private Classes classes;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}
