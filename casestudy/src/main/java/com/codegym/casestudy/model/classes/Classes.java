package com.codegym.casestudy.model.classes;


import com.codegym.casestudy.model.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;


import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "classes_name")
    private String name;
    private int status;
    @OneToMany(mappedBy = "classes")
    private Set<Student> studentSet;
}
