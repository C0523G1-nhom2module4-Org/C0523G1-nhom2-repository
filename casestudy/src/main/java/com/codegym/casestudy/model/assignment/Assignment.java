package com.codegym.casestudy.model.assignment;

import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.teacher.Teacher;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, columnDefinition = " cdesc")
    private String dateStart;
    @Column(nullable = false, columnDefinition = "DATE")
    private String dateEnd;

    @OneToMany(mappedBy = "assignment")
    @JoinColumn(columnDefinition = "class_id", referencedColumnName = "id")
    private Set<Teacher> teacher;

    @OneToMany
    @JoinColumn(columnDefinition = "class_id", referencedColumnName = "id")
    private Classes classes;
}
