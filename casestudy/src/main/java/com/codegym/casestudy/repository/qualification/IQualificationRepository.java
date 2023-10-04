package com.codegym.casestudy.repository.qualification;

import com.codegym.casestudy.model.qualification.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQualificationRepository extends JpaRepository<Qualification, Long> {
    Qualification findByName(String qualificationName);
}
