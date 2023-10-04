package com.codegym.casestudy.service.classes;

import com.codegym.casestudy.dto.classes.ListClassesDto;
import com.codegym.casestudy.dto.student.ListStudentDto;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.repository.classes.IClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService implements IClassesService {
    @Autowired
    private IClassesRepository classesRepository;

    @Override
    public List<ListClassesDto> findAllClass(int id) {
        return classesRepository.findAllClass(id);
    }

    @Override
    public List<Classes> findAll() {
        return classesRepository.findAll();
    }

    @Override
    public void add(Classes classes) {
        classesRepository.save(classes);
    }

    @Override
    public void delete(Classes classes) {
        classesRepository.deleteClass(classes);
    }

    @Override
    public Page<Classes> findClass(Pageable pageable, String name) {
        return classesRepository.listClass(pageable, "%" + name + "%");
    }

    @Override
    public Classes findById(int id) {
        return classesRepository.findById(id).get();
    }

    @Override
    public Page<ListStudentDto> findStudent(Pageable pageable, int idClass) {
        return classesRepository.listStudent(pageable, idClass);
    }
}
