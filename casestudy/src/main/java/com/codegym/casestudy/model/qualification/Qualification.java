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

    @Column(length = 30, nullable = false, unique = true, name = "qualification_name")
    private String name;

    @Column(nullable = false, name = "qualification_fee", columnDefinition = "BIGINT")
    private Long fee;

    @Column(columnDefinition = "LONGTEXT", nullable = false, name = "qualification_description")
    private String description;


    public Qualification(String name, Long fee, String description) {
        this.name = name;
        this.fee = fee;
        this.description = description;
    }
}
