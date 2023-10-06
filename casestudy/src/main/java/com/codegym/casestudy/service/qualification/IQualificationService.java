package com.codegym.casestudy.service.qualification;

import com.codegym.casestudy.dto.qualification.QualificationDto;
import com.codegym.casestudy.model.qualification.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IQualificationService {
    List<Qualification> findAll();
    void add(Qualification qualification);
    boolean isExist(String qualificationName);
    boolean remove(Long qualificationId);
    Page<Qualification> findAllQualification(Pageable pageable, String qualificationName);
    Qualification findById(Long qualificationId);
}
