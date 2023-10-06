package com.codegym.casestudy.service.student;

import com.codegym.casestudy.dto.student.StudentDto;
import com.codegym.casestudy.model.student.Student;
import com.codegym.casestudy.repository.student.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Page<StudentDto> findAllStudent(Pageable pageable, String name) {
        return studentRepository.loadStudents(pageable, "%" + name + "%");
    }

    @Override
    public void add(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(Student student) {
        studentRepository.deleteId(student);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void edit(int id, Student student) {
        studentRepository.save(student);
    }
}
