package com.codegym.casestudy.model.contract;

import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.model.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fee",columnDefinition = "bigint",nullable = false)
    private Long fee;
    @Column(name = "contract_date",columnDefinition = "date",nullable = false)
    private String date;

    @OneToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "qualification_id",referencedColumnName = "id")
    private Qualification qualification;

    @Column(name = "is_deleted")
    private int isDeleted;


}
