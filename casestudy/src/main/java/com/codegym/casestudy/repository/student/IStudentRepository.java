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

@Repository
public interface IStudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT " +
            "    classes_name as className, " +
            "    id as studentId," +
            "    student_birthday as studentBirth, " +
            "    student_gender as  studentGender, " +
            "    student_identity as studentIdentity, " +
            "    student_name as studentName, " +
            "    student_phone_number as studentPhoneNumber," +
            "    status as studentStatus " +
            "   FROM " +
            "      student st " +
            "   left JOIN classes cla ON st.classes_id = cla.id " +
            "    where st.student_name like :studentName and st.status = 1 ", nativeQuery = true)
    Page<StudentDto> findAllStudent(Pageable pageable, @Param("studentName") String studentName);

    @Modifying
    @Transactional
    @Query(value = " update student set status = 0 where id=:id", nativeQuery = true)
    void deleteId(@Param(value = "id") Student student);

}
