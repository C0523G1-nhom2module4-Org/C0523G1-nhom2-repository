package com.codegym.casestudy.service.assignment;

import com.codegym.casestudy.model.assignment.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAssignmentService {
    Page<Assignment> findAll(Pageable pageable, String searchName);

    void add(Assignment assignment);

    boolean update(Assignment assignment);

    boolean delete(Long id);

    Assignment findById(Long id);
}
