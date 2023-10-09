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
public class AssignmentDtoForShow {
    private String dayStart;
    private String dayEnd;
    private Classes classes;
    private Teacher teacher;
    // for show assignment list
}
