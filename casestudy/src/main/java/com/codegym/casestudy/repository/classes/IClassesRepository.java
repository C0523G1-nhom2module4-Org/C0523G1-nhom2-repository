package com.codegym.casestudy.repository.classes;

import com.codegym.casestudy.dto.classes.ClassDetailDto;
import com.codegym.casestudy.dto.classes.ListClassesDto;
import com.codegym.casestudy.dto.student.ListStudentDto;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClassesRepository extends JpaRepository<Classes, Integer> {
    @Query(value = "SELECT c.id AS classId, c.class_name AS className,  " +
            " tea.teacher_name AS name, COUNT(st.id) AS studentCount " +
            " FROM classes c " +
            " LEFT JOIN students st ON st.class_id = c.id " +
            " LEFT JOIN assignments ass ON ass.class_id = c.id " +
            " LEFT JOIN teachers tea ON tea.id = ass.teacher_id " +
            " WHERE c.id = :classId and c.is_deleted = 0 " +
            " GROUP BY c.id, c.class_name, tea.teacher_name ", nativeQuery = true)
    List<ListClassesDto> findAllClass(@Param(value = "classId") int classId);

    @Modifying
    @Transactional
    @Query(value = "update classes set is_deleted = false where id=:id", nativeQuery = true)
    void deleteClass(@Param(value = "id") Classes classes);

    @Query(value = "select * from classes where is_deleted =0 and class_name like :name", nativeQuery = true)
    Page<Classes> listClass(Pageable pageable, @Param(value = "name") String name);

    @Query(value = "SELECT " +
            "    cla.id as classId, " +
            "    cla.class_name as className, " +
            "    st.id as id, " +
            "    st.birthday as birthday, " +
            "    st.gender as gender, " +
            "    st.identity as identity, " +
            "    st.student_name as studentName, " +
            "    st.phone as phone " +
            "   FROM " +
            "      students st " +
            "   left JOIN classes cla ON st.class_id = cla.id " +
            "    where cla.id = :classId and cla.is_deleted = 0 " +
            "     ", nativeQuery = true)
    Page<ListStudentDto> listStudent(Pageable pageable, @Param("classId") int classId);

    @Query(value = " select c.class_name, c.class_description, c.class_start_date,  c.class_end_date, " +
            " t.teacher_name, s.student_name " +
            " from classes as c " +
            " left join assignments as a " +
            " on c.id = a.teacher_id " +
            " left join teachers as t " +
            " on a.teacher_id = t.id " +
            " left join students as s" +
            " on c.id = s.class_id " +
            " where (c.class_name like :className) " +
            " and (s.is_deleted = 0) " +
            " order by c.class_name ",
            nativeQuery = true)
    ClassDetailDto getClassesByClassNameEquals(@Param(value = "className") String className);

    @Query(value = " select s.student_name from students as s " +
            "right join classes as c " +
            "on c.id = s.class_id " +
            "where c.class_name like :className " +
            "and s.is_deleted = 0 " +
            "order by s.student_name ",
            nativeQuery = true)
    List<String> findAllByClassName(@Param(value = "className") String className);
}
