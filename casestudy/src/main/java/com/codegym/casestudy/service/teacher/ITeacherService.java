package com.codegym.casestudy.service.teacher;

import com.codegym.casestudy.dto.teacher.ITeacherDto;
import com.codegym.casestudy.model.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITeacherService {
    Page<ITeacherDto> searchByName(Pageable pageable, String searchName);

    void saveNewTeacher(Teacher teacher);

    Teacher findById(int id);

    void delete(Teacher teacher);

    List<Teacher> findAll();

    void updateTeacher( Teacher teacher);
}
