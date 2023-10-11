package com.codegym.casestudy.service.assignment;

import com.codegym.casestudy.dto.classes.ClassesDtoAssignment;
import com.codegym.casestudy.model.assignment.Assignment;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.teacher.Teacher;
import com.codegym.casestudy.model.teacher.TeacherDtoAssignment;
import com.codegym.casestudy.repository.assignment.IAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AssignmentService implements IAssignmentService {
    @Autowired
    private IAssignmentRepository assignmentRepository;

    @Override
    public Page<Assignment> findAll(Pageable pageable, String searchName) {
        return this.assignmentRepository.findAllBySearch(pageable, "%" + searchName + "%");
    }

    @Override
    @Transactional
    public void add(Assignment assignment) {
        try {
            this.assignmentRepository.save(assignment);
        } catch (Exception e) {
            System.out.println("Error while adding assignment: " + e.getMessage());
        }
    }

    @Override
    public Assignment findById(Long id) {
        Assignment existedAssignment = this.assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cant found assignment with id: " + id));
        return existedAssignment;
    }

    @Override
    public boolean update(Assignment assignment) {
        Assignment existedAssignment = this.assignmentRepository.findById(assignment.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cant found assignment " + assignment));
        if (existedAssignment == null) {
            return false;
        } else {
            try {
                this.assignmentRepository.save(assignment);
            } catch (Exception e) {
                System.out.println("Error while updating assignment: " + e.getMessage());
            }
            return true;
        }
    }

    @Override
    public boolean delete(Long id) {
        Assignment existedAssignment = this.assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment is not exist"));
        if (existedAssignment == null) {
            return false;
        } else {
            try {
                this.assignmentRepository.delete(existedAssignment);
            } catch (Exception e) {
                System.out.println("Error while updating assignment: " + e.getMessage());
            }
            return true;
        }
    }

    @Override
    public List<ClassesDtoAssignment> findAllClassAvailable() {
        return this.assignmentRepository.findAllClassAvailable();
    }

    @Override
    public List<TeacherDtoAssignment> findAllTeacherAvailable() {
        return this.assignmentRepository.findAllTeacherAvailable();
    }
}
