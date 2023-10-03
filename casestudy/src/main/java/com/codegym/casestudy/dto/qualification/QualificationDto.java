package com.codegym.casestudy.dto.qualification;


import javax.validation.constraints.NotBlank;

public class QualificationDto {
    @NotBlank(message = "Please fill this field")
    private String name;
    @NotBlank(message = "Please fill this field")
    private String description;

    public QualificationDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public QualificationDto() {
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
