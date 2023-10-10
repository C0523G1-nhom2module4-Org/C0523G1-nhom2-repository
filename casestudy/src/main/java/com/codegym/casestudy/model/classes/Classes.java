package com.codegym.casestudy.model.classes;


import com.codegym.casestudy.model.assignment.Assignment;
import com.codegym.casestudy.model.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return id == classes.id && Objects.equals(className, classes.className) && Objects.equals(description, classes.description) && Objects.equals(startDate, classes.startDate) && Objects.equals(endDate, classes.endDate) && Objects.equals(studentSet, classes.studentSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, description, startDate, endDate, studentSet);
    }
}
