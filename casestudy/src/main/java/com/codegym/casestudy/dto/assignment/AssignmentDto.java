package com.codegym.casestudy.dto.assignment;

import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDto {
    private Long id;
    private String dayStart;
    private String dayEnd;
    private Classes classes;
    private Teacher teacher;

    public AssignmentDto(String dayStart, String dayEnd, Classes classes, Teacher teacher) {
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.classes = classes;
        this.teacher = teacher;
    }
    // for adding new assignment
}
