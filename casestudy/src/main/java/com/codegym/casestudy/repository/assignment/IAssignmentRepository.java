package com.codegym.casestudy.repository.assignment;

import com.codegym.casestudy.model.assignment.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IAssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query(value = " select * from assignments " +
            "left join teachers on assignments.teacher_id = teachers.id " +
            "left join classes on assignments.class_id = classes.id " +
            "where assignments.is_deleted = 0 and ( " +
            "teachers.teacher_name like :search or " +
            "classes.class_name like :search ) " +
            "order by assignments.date_start ",
            nativeQuery = true)
    Page<Assignment> findAllBySearch(Pageable pageable, @Param(value = "search") String search);
}
