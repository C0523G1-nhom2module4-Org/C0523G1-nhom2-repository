package com.codegym.casestudy.service.qualification;

import com.codegym.casestudy.model.qualification.Qualification;

import java.util.List;

public interface IQualificationService {
    List<Qualification> findAll();
    void add(Qualification qualification);
    boolean isExist(String qualificationName);
    boolean remove(Long qualificationId);
}
