package com.codegym.casestudy.service.assignment;

import com.codegym.casestudy.dto.classes.ClassesDtoAssignment;
import com.codegym.casestudy.model.assignment.Assignment;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.teacher.Teacher;
import com.codegym.casestudy.model.teacher.TeacherDtoAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAssignmentService {
    Page<Assignment> findAll(Pageable pageable, String searchName);

    void add(Assignment assignment);

    boolean update(Assignment assignment);

    boolean delete(Long id);

    Assignment findById(Long id);

    List<ClassesDtoAssignment> findAllClassAvailable();

    List<TeacherDtoAssignment> findAllTeacherAvailable();

}
