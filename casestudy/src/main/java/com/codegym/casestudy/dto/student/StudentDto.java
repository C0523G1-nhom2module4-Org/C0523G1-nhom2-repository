package com.codegym.casestudy.dto.student;


public interface StudentDto {
    int getId();
    int getClassId();
    String getClassName();

    String getStudentName();

    String getIdentity();

    int getGender();

    String getBirthday();

    String getPhone();

    int getGraduatePoint();

    String getAddress();

    boolean getIsDeleted();
}
