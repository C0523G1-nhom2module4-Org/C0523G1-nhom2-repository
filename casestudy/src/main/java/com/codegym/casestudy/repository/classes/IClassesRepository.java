package com.codegym.casestudy.repository.classes;

import com.codegym.casestudy.dto.classes.ListClassesDto;
import com.codegym.casestudy.dto.student.ListStudentDto;
import com.codegym.casestudy.model.classes.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClassesRepository extends JpaRepository<Classes, Integer> {
    @Query(value = "SELECT id AS classId, classes_name AS className, " +
            "teacher_name AS name, COUNT(st.id) AS studentCount " +
            "FROM classes cla " +
            "LEFT JOIN students st ON st.classes_id = cla.id " +
            "LEFT JOIN assignments ass ON ass.classes_id = cla.id " +
            "LEFT JOIN teachers tea ON tea.id = ass.teacher_id " +
            "WHERE cla.id = :classId and cla.is_deleted = 1 " +
            "GROUP BY cla.id, cla.classes_name, tea.teacher_name, ", nativeQuery = true)
    List<ListClassesDto> findAllClass(@Param(value = "classId") int classId);

    @Modifying
    @Transactional
    @Query(value = "update classes set is_deleted = false where id=:id", nativeQuery = true)
    void deleteClass(@Param(value = "id") Classes classes);

    @Query(value = "select * from classes where is_deleted =0 and classes_name like :name", nativeQuery = true)
    Page<Classes> listClass(Pageable pageable, @Param(value = "name") String name);

    @Query(value = "SELECT " +
            "    cla.id as classId, " +
            "    cla.classes_name as className, " +
            "    st.id as studentId, " +
            "    st.student_birthday as studentBirthday, " +
            "    st.student_gender as studentGender, " +
            "    st.identity as studentIdentity, " +
            "    st.student_name as studentName, " +
            "    st.student_phone_numner as studentPhoneNUmber " +
            "   FROM " +
            "      student st " +
            "   left JOIN classes cla ON st.classes_id = cla.id " +
            "    where cla.id = :classId and cla.status = 1 " +
            "     ", nativeQuery = true)
    Page<ListStudentDto> listStudent(Pageable pageable, @Param("classId") int classId);
}
