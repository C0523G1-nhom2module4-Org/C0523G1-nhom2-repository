package com.codegym.casestudy.model.classes;


import com.codegym.casestudy.model.assignment.Assignment;
import com.codegym.casestudy.model.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "class_name")
    private String className;
    @Column(name = "class_description")
    private String description;
    @Column(name = "class_start_date")
    private String startDate;
    @Column(name = "class_end_date")
    private String endDate;


    @OneToMany(mappedBy = "classes")
    private Set<Student> studentSet;


}
