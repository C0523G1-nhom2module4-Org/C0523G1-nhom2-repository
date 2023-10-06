package com.codegym.casestudy.dto.contract;

import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.model.student.Student;

public interface IContractDto {
    int getId();
    long getFee();
    String getDate();
    int getStudentId();
    int getQualificationId();

}
