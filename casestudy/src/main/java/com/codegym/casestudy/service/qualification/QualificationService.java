package com.codegym.casestudy.service.qualification;


import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.repository.qualification.IQualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QualificationService implements IQualificationService {
    @Autowired
    private IQualificationRepository qualificationRepository;


    @Transactional
    @Override
    public void add(Qualification qualification) {
        try {
            this.qualificationRepository.save(qualification);
        } catch (Exception e) {
            System.out.println("Error while changing qualification data: " + e.getMessage());
        }
    }

    @Override
    public boolean isExist(String qualificationName) {
        Qualification qualification = this.qualificationRepository.findQualificationByNameEquals(qualificationName);
        return qualification != null;
    }

    @Transactional
    @Override
    public boolean remove(Long qualificationId) {
        try {
            Qualification existedQualification = this.qualificationRepository.findById(qualificationId).get();
            if (existedQualification != null) {
                this.qualificationRepository.deleteById(qualificationId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Qualification> findAll() {
        return this.qualificationRepository.findAll();
    }

    @Override
    public Page<Qualification> findAllQualification(Pageable pageable, String qualificationName) {
        String containName = "%" + qualificationName + "%";
        return this.qualificationRepository.findAllQualification(pageable, containName);
    }


    @Override
    public Qualification findById(Long qualificationId) {
        try {
            Qualification existQualification = this.qualificationRepository.findById(qualificationId).get();
            return existQualification;
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
