package com.codegym.casestudy.service.classes;

import com.codegym.casestudy.dto.classes.ClassDetailDto;
import com.codegym.casestudy.dto.classes.ListClassesDto;
import com.codegym.casestudy.dto.student.ListStudentDto;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClassesService {
    List<ListClassesDto> findAllClass(int id);

    List<Classes> findAll();

    void add(Classes classes);

    void delete(Classes classes);

    Page<Classes> findClass(Pageable pageable, String name);

    Classes findById(int id);

    void edit(Classes classes);


    Page<ListStudentDto> findStudent(Pageable pageable, int idClass);

    ClassDetailDto getClassDetail(String className);

    List<String> findAllByClassName(@Param(value = "className") String className);

}
