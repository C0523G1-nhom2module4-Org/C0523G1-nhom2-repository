package com.codegym.casestudy.service.qualification;

import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.repository.qualification.IQualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            System.out.println("Error while adding qualification: " + e.getMessage());
        }
    }

    @Override
    public boolean isExist(String qualificationName) {
        Qualification qualification = this.qualificationRepository.findByName(qualificationName);
        return qualification != null;
    }

    @Transactional
    @Override
    public boolean remove(Long qualificationId) {
        try {
            Qualification existedQualification = this.qualificationRepository.findById(qualificationId).get();
            if (existedQualification != null) {
                this.qualificationRepository.delete(existedQualification);
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
}
