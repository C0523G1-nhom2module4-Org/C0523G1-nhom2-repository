package com.codegym.casestudy.model.teacher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Teacher {
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
    private double salary;
    private boolean status;

}
