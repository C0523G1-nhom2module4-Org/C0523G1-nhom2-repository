package com.codegym.casestudy.repository.student;

import com.codegym.casestudy.dto.student.ListStudentDto;
import com.codegym.casestudy.dto.student.StudentDto;
import com.codegym.casestudy.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IStudentRepository extends JpaRepository<Student, Integer> {


    @Query(value = "select s.id AS id, s.student_name as studentName, s.class_id as classId, c.class_name as className, s.gender as gender, " +
            "s.identity as identity, s.birthday as birthday, s.phone as phone, s.address as address, " +
            "s.is_deleted as isDeleted, s.graduate_point as graduatePoint from students AS s " +
            " left join classes as c on s.class_id = c.id " +
            " where s.student_name like :searchName and s.is_deleted = 0 ", nativeQuery = true)
    Page<StudentDto> loadStudents(Pageable pageable, @Param("searchName") String searchName);


    @Modifying
    @Transactional
    @Query(value = " update students set is_deleted = 1 where id= :id", nativeQuery = true)
    void deleteId(@Param(value = "id") int id);

    @Modifying
    @Transactional
    @Query(value = " update students set is_deleted = 1 where id= :id", nativeQuery = true)
    void updateById(@Param(value = "id")int id);
}
