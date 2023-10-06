package com.codegym.casestudy.dto.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDetailDto {
    private String className;
    private String description;
    private String classStartDate;
    private String classEndDate;
    private String teacherName;
    private List<String> studentName;
}
