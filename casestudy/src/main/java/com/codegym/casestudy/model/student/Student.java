package com.codegym.casestudy.model.student;

import com.codegym.casestudy.dto.account.Account;
import com.codegym.casestudy.model.classes.Classes;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String identity;
    private boolean gender;
    private LocalDate birthday ;
    private String phoneNumber;
    private double point;
    private String address;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "classes_id", referencedColumnName = "id")
    private Classes classes;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    public Student() {
    }

    public Student(int id, String name, String identity, boolean gender, LocalDate birthday, String phoneNumber, double point, String address, boolean status, Classes classes, Account account) {
        this.id = id;
        this.name = name;
        this.identity = identity;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.address = address;
        this.status = status;
        this.classes = classes;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
