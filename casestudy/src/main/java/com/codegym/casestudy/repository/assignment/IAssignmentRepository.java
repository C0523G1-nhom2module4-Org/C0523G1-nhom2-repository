package com.codegym.casestudy.repository.assignment;

import com.codegym.casestudy.model.assignment.Assignment;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    @Query(value = " select classes.id as id, classes.class_name as className" +
            ", classes.class_description as description, classes.class_start_date as startDay, " +
            " classes.class_end_date as endDate " +
            " from classes " +
            " left join assignments " +
            " on classes.id = assignments.class_id " +
            " where assignments.class_id IS NULL " +
            " AND classes.is_deleted = 0 ", nativeQuery = true)
    List<Classes> findAllClassAvailable();

    @Query(value = " select teachers.id as id, teachers.teacher_name as name, " +
            " teachers.identity as identity, teachers.gender as gender, " +
            " teachers.birthday as birthday, teachers.phone as phone, " +
            " teachers.address as address, teachers.salary as salary," +
            " from teachers " +
            " left join assignments " +
            " on teachers.id = assignments.teacher_id " +
            " where assignments.teacher_id IS NULL " +
            " AND teachers.is_deleted = 0", nativeQuery = true)
    List<Teacher> findAllTeacherAvailable();
}
