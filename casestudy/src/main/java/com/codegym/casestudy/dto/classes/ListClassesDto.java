package com.codegym.casestudy.dto.classes;

public interface ListClassesDto {
    int getClassId();
    String getClassName();
    String getStartDate();
    String getEndDate();
    int getIsDeleted();
    String getTeacherName();
    String getDescription();
    int getStudentCount();
}
