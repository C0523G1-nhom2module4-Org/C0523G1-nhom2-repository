package com.codegym.casestudy.model.assignment;

import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, columnDefinition = "DATE", name = "date_start")
    private String dateStart;
    @Column(nullable = false, columnDefinition = "DATE", name = "date_end")
    private String dateEnd;

    @OneToOne()
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;

    @OneToOne()
    @JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private Classes classes;
}
