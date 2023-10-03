package com.codegym.casestudy.model.qualification;

import javax.persistence.*;

@Entity
@Table(name = "qualifications")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String description;

    public Qualification(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Qualification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
