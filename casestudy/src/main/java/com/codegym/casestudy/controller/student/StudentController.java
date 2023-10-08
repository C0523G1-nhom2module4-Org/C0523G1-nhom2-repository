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
    public String showList(@RequestParam(defaultValue = "", required = false) String name, Model model,
                           @RequestParam(defaultValue = "0", required = false) int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("student_name").ascending());
        Page<StudentDto> studentDtos = studentService.findAllStudent(pageable, name);
        model.addAttribute("studentDtos", studentDtos);
        model.addAttribute("name", name);
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
            System.out.println("Loi");
            return "/student/add";
        }
        Student student = new Student();
        BeanUtils.copyProperties(listStudentDto, student);
        studentService.add(student);
        redirectAttributes.addFlashAttribute("mess", "thêm mới thành công");
        System.out.println("OK");
        return "redirect:/student";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") int id, RedirectAttributes redirectAttributes) {
        this.studentService.deleteWithId(id);
        redirectAttributes.addFlashAttribute("mess", "Xoá Thành Côngg");
        return "redirect:/student";
    }

    @GetMapping("/edit")
    public String showEdit(@RequestParam int id, Model model) {
        List<Classes> classesList = classesService.findAll();
        Student student = studentService.findById(id);
        ListStudentDto listStudentDto = new ListStudentDto();
        BeanUtils.copyProperties(student, listStudentDto);
        model.addAttribute("classesList", classesList);
        model.addAttribute("listStudentDto", listStudentDto);
        return "/student/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute ListStudentDto listStudentDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes, Model model) {
        List<Classes> classesList = classesService.findAll();
        new ListStudentDto().validate(listStudentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("classesList", classesList);
            return "/student/edit";
        }
        Student student = new Student();
        BeanUtils.copyProperties(listStudentDto, student);
        studentService.edit(student);
        redirectAttributes.addFlashAttribute("mess", "Sửa Thành Công");
        return "redirect:/student";
    }
}