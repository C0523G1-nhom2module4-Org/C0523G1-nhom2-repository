package com.codegym.casestudy.service.student;

import com.codegym.casestudy.dto.student.ListStudentDto;
import com.codegym.casestudy.dto.student.StudentDto;
import com.codegym.casestudy.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    Page<StudentDto> findAllStudent(Pageable pageable, String name);

    void add(Student student);

    List<Student> findAll();

    void delete(Student student);

    Student findById(int id);

    void edit( Student student);

    void deleteWithId(int id);

}
