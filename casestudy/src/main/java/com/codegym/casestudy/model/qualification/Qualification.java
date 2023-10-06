package com.codegym.casestudy.model.qualification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "qualifications")
@Getter
@Setter
@NoArgsConstructor
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30, unique = true, nullable = false, name = "qualification_name")
    private String name;

    @Column(columnDefinition = "BIGINT", nullable = false, name = "qualification_fee")
    private Long fee;
    @Column(columnDefinition = "int", nullable = false, name = "course_duration")
    private Integer courseDuration;

    @Column(columnDefinition = "LONGTEXT", nullable = false, name = "qualification_description")
    private String description;

    public Qualification(String name, Long fee, Integer courseDuration, String description) {
        this.name = name;
        this.fee = fee;
        this.courseDuration = courseDuration;
        this.description = description;
    }
}
