package com.codegym.casestudy.controller.classes;

import com.codegym.casestudy.dto.classes.ClassDetailDto;
import com.codegym.casestudy.dto.classes.ClassesDto;
import com.codegym.casestudy.dto.classes.ListClassesDto;
import com.codegym.casestudy.dto.student.ListStudentDto;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.student.Student;
import com.codegym.casestudy.service.classes.IClassesService;
import com.codegym.casestudy.service.student.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private IClassesService classesService;
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public String showClass(@RequestParam(defaultValue = "", required = false) String name,
                            Model model, @RequestParam(defaultValue = "0", required = false) int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Classes> classList = classesService.findClass(pageable, name);
        model.addAttribute("classList", classList);
        model.addAttribute("name", name);
        return "/classes/list";
    }

    @GetMapping("/listStudent")
    public String showStudent(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "-1", required = false) int id, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<ListStudentDto> studentDtoPage = classesService.findStudent(pageable, id);
        List<ListClassesDto> classesDto = classesService.findAllClass(id);
        model.addAttribute("classesDto", classesDto);
        model.addAttribute("studentDtoPage", studentDtoPage);
        model.addAttribute("id", id);
        return "/classes/newDetail";
    }

    @ModelAttribute("classesList")
    public List<Classes> list() {
        return classesService.findAll();
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        ClassesDto classDto = new ClassesDto();
        model.addAttribute("classDto", classDto);
        return "/classes/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("classDto") ClassesDto classDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        new ClassesDto().validate(classDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/classes/add";
        }
        Classes classes = new Classes();
        BeanUtils.copyProperties(classDto, classes);
        classesService.add(classes);
        redirectAttributes.addFlashAttribute("mess", "thêm thành công");
        return "redirect:/classes";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {
        Classes classes = classesService.findById(id);
        ClassesDto classesDto = new ClassesDto();
        BeanUtils.copyProperties(classes, classesDto);
        model.addAttribute("classesDto", classesDto);
        return "/classes/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute ClassesDto classesDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        ClassesDto classesDto1= new ClassesDto();
        classesDto.setClassList(classesService.findAll());
        classesDto1.validate(classesDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "/classes/edit";
        }
        Classes classes = new Classes();
        BeanUtils.copyProperties(classesDto, classes);
//        classes.setDeleted(false);
        classesService.edit(classes);
        redirectAttributes.addFlashAttribute("message", "Sửa thành công");
        return "redirect:/classes";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Classes classes = classesService.findById(id);
        classesService.delete(classes);
        redirectAttributes.addFlashAttribute("mess", "xoá thành công");
        return "redirect:/classes";
    }


    // thien
    @GetMapping("/class-detail/{classId}")
    public String classDetail(@PathVariable(name = "classId") int classId,
                              Model model) {
        Classes classes = this.classesService.findById(classId);
        String className = classes.getClassName();
        List<String> studentList = this.classesService.findAllByClassName(className);
        model.addAttribute("studentList",studentList);
        model.addAttribute("classes",classes);
        return "/classes/class-detail";
    }
}
