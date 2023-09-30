package com.codegym.casestudy.model.classes;

import com.codegym.casestudy.model.student.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean status;
    @OneToMany(mappedBy = "classes")
    private Set<Student> studentSet;

    public Classes() {
    }

    public Classes(int id, String name, Set<Student> studentSet) {
        this.id = id;
        this.name = name;
        this.studentSet = studentSet;
    }

    public Classes(int id, String name, boolean status, Set<Student> studentSet) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.studentSet = studentSet;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }
}
