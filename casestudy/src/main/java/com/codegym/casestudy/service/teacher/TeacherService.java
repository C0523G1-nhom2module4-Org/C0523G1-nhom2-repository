package com.codegym.casestudy.service.teacher;

import com.codegym.casestudy.dto.teacher.ITeacherDto;
import com.codegym.casestudy.model.teacher.Teacher;
import com.codegym.casestudy.repository.teacher.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
//    public Page<ITeacherDto> searchByName(Pageable pageable, String searchName) {
//        return teacherRepository.findAll(pageable, "%" + searchName + "%");
//    }
        public Page<ITeacherDto> searchByName(Pageable pageable, String searchName) {
        return teacherRepository.loadTeachers(pageable,"%" + searchName + "%");
    }

    @Override
//    public void saveNewTeacher(Teacher teacher) {
//        teacherRepository.saveNewTeacher(teacher.getName(), teacher.getGender(), teacher.getBirthday(),
//                teacher.getIdentity(), teacher.getSalary(), teacher.getPhone(), teacher.getAddress());
//    }

        public void saveNewTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherRepository.deleteTeacher(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void updateTeacher(int id, Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
