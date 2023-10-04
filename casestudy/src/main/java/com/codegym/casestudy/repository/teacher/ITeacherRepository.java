package com.codegym.casestudy.repository.teacher;

import com.codegym.casestudy.dto.teacher.ITeacherDto;
import com.codegym.casestudy.model.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ITeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query(value = "select id, " +
            "teacher_name as name, " +
            "teacher_gender as gender, " +
            "teacher_birth as birth, " +
            "teacher_salary as salary, " +
            "teacher_phone as phone, " +
            "teacher_address as address " +
            "from teacher where teacher_status = true and teacher_name like :name", nativeQuery = true)
    Page<ITeacherDto> findTeacherByNameContaining(Pageable pageable, @Param("name") String searchName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO teacher (teacher_address," +
            "teacher_birthday, " +
            "teacher_identity, " +
            "teacher_gender, " +
            "teacher_name, " +
            "teacher_phone_number, " +
            "teacher_salary, " +
            "teacher_status) " +
            "VALUES (:address, :birthday, :identity, :gender, :name, :phoneNumber, :salary, 1)", nativeQuery = true)
    void saveNewTeacher(@Param("name") String name, @Param("gender") int gender, @Param("birthday") String  birthday,
                        @Param("identity") String  identity, @Param("salary") int salary, @Param("phoneNumber") String phoneNumber,
                        @Param("address") String address);

    @Modifying
    @Transactional
    @Query(value = "update teacher set teacher_status = 0 where id = :id", nativeQuery = true)
    void deleteTeacher(@Param("id") Teacher teacher);

}
