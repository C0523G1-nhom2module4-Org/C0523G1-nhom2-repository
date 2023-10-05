package com.codegym.casestudy.model.classes;


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
    @Column(name = "classes_name")
    private String className;
    @Column(name = "is_deleted")
    private boolean isDeleted;


    @OneToMany(mappedBy = "classes")
    private Set<Student> studentSet;

}
