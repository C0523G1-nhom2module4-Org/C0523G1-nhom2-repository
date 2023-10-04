package com.codegym.casestudy.controller.student;

import com.codegym.casestudy.dto.student.ListStudentDto;
import com.codegym.casestudy.dto.student.StudentDto;
import com.codegym.casestudy.model.student.Student;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.service.classes.IClassesService;
import com.codegym.casestudy.service.student.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassesService classesService;

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "", required = false) String studentName, Model model,
                           @RequestParam(defaultValue = "0", required = false) int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("studentName").descending());
        Page<StudentDto> studentDtos = studentService.findAllStudent(pageable, studentName);
        model.addAttribute("studentDtos", studentDtos);
        model.addAttribute("studentName", studentName);
        return "/student/list";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        List<Classes> classes = classesService.findAll();
        model.addAttribute("classes", classes);
        model.addAttribute("listStudentDto", new ListStudentDto());
        return "/student/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute ListStudentDto listStudentDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        new ListStudentDto().validate(listStudentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/student/add";
        }
        Student student = new Student();
        student.setStatus(1);
        BeanUtils.copyProperties(listStudentDto, student);
        studentService.add(student);
        redirectAttributes.addFlashAttribute("mess", "thêm mới thành công");
        return "redirect:/student";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Student student = studentService.findById(id);
        studentService.delete(student);
        redirectAttributes.addFlashAttribute("mess", "Xoá Thành Công");
        return "redirect:/student";
    }

    @GetMapping("/edit")
    public String showEdit(@RequestParam int id, Model model) {
        List<Classes> classesList = classesService.findAll();
        Student student = studentService.findById(id);
        model.addAttribute("classesList", classesList);
        model.addAttribute("student", student);
        return "/student/edit";
    }

    @PostMapping("/edit")
    public String edit(int id, @Valid @ModelAttribute ListStudentDto listStudentDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        new ListStudentDto().validate(listStudentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/student/edit";
        }
        Student student = new Student();
        student.setStatus(1);
        BeanUtils.copyProperties(listStudentDto, student);
        studentService.edit(id, student);
        redirectAttributes.addFlashAttribute("mess", "Sửa Thành Công");
        return "redirect:/student";
    }
}