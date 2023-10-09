package com.codegym.casestudy.dto.assignment;

import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDtoForEdit {
    private Long id;
    private LocalDate dayStart;
    private LocalDate dayEnd;
    private Classes classes;
    private Teacher teacher;
    // for adding new assignment
}