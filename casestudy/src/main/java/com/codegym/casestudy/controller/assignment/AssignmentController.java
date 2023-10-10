package com.codegym.casestudy.controller.assignment;

import com.codegym.casestudy.dto.assignment.AssignmentDto;
import com.codegym.casestudy.dto.assignment.AssignmentDtoForEdit;
import com.codegym.casestudy.dto.classes.ClassesDtoAssignment;
import com.codegym.casestudy.model.assignment.Assignment;
import com.codegym.casestudy.model.classes.Classes;
import com.codegym.casestudy.model.teacher.Teacher;
import com.codegym.casestudy.model.teacher.TeacherDtoAssignment;
import com.codegym.casestudy.service.assignment.IAssignmentService;
import com.codegym.casestudy.service.classes.IClassesService;
import com.codegym.casestudy.service.teacher.ITeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/assignment")
public class AssignmentController {
    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IClassesService classesService;

    //main page + back
    @GetMapping()
    public String assignmentList(@RequestParam(defaultValue = "0", required = false) Integer page,
                                 @RequestParam(defaultValue = "", required = false) String searchName,
                                 Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Assignment> assignments = this.assignmentService.findAll(pageable, searchName);
        model.addAttribute("searchName", searchName);
        model.addAttribute("page", page);
        model.addAttribute("assignments", assignments);
        return "assignment-list";
    }

    @GetMapping("/back")
    public String back() {
        return "redirect:/admin/assignment";
    }

    // add
    @GetMapping("/add")
    public String showAssignmentAddForm(Model model) {
        List<ClassesDtoAssignment> classList = this.assignmentService.findAllClassAvailable();
        List<TeacherDtoAssignment> teacherList = this.assignmentService.findAllTeacherAvailable();
        AssignmentDto assignmentDto = new AssignmentDto();

        model.addAttribute("classList", classList);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("assignmentDto", assignmentDto);
        return "assignment-add";
    }

    @PostMapping("/add")
    public String assignmentAdd(@ModelAttribute AssignmentDto assignmentDto,
                                RedirectAttributes redirectAttributes) {
        Assignment assignment = new Assignment();
        BeanUtils.copyProperties(assignmentDto, assignment);
        this.assignmentService.add(assignment);
        String addSuccess = "Phân công thành công";
        redirectAttributes.addFlashAttribute("addSuccess", addSuccess);
        return "redirect:/admin/assignment";
    }

    // delete
    @GetMapping("/delete")
    public String deleteAssignment(@RequestParam(name = "id") Long id,
                                   RedirectAttributes redirectAttributes) {
        String message = null;
        Assignment existedAssignment = this.assignmentService.findById(id);

        if (existedAssignment == null) {
            message = "Bản phân công này không còn tồn tại";
        }

        this.assignmentService.delete(id);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/admin/assignment";
    }

    //edit
    @GetMapping("/edit/{assignmentId}")
    public String showAssignmentEdit(@PathVariable(name = "assignmentId") Long assignmentId,
                                     Model model, RedirectAttributes redirectAttributes) {
        Assignment existedAssignment = this.assignmentService.findById(assignmentId);
        if (existedAssignment == null) {
            redirectAttributes.addFlashAttribute("message", "Phân công không còn tồn tại");
            return "redirect:/admin/assignment";
        }

        List<Classes> classList = this.classesService.findAll();
        List<Teacher> teacherList = this.teacherService.findAll();

        AssignmentDtoForEdit assignmentDto = new AssignmentDtoForEdit();
        BeanUtils.copyProperties(existedAssignment, assignmentDto);

        model.addAttribute("assignmentDto", assignmentDto);
        model.addAttribute("classList", classList);
        model.addAttribute("teacherList", teacherList);

        return "/assignment-edit";
    }

    @PostMapping("/edit")
    public String editAssigment(@ModelAttribute AssignmentDto assignmentDto) {
        //validate
        Assignment assignment = new Assignment();
        BeanUtils.copyProperties(assignmentDto, assignment);
        this.assignmentService.add(assignment);
        return "redirect:/admin/assignment";
    }
}

