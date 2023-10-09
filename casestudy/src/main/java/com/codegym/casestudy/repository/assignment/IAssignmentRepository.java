package com.codegym.casestudy.repository.assignment;

import com.codegym.casestudy.model.assignment.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IAssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query(value = " select * from assignments where is_deleted = 0 ", nativeQuery = true)
    Page<Assignment> findAllBySearch(Pageable pageable, String search);
}
