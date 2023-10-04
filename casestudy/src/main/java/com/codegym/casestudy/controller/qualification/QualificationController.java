package com.codegym.casestudy.controller.qualification;

import com.codegym.casestudy.dto.qualification.QualificationDto;
import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.service.qualification.IQualificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class QualificationController {
    @Autowired
    private IQualificationService qualificationService;

    //show list
    @GetMapping("/qualification")
    public String qualificationList(Model model) {
        List<Qualification> qualifications = this.qualificationService.findAll();
        if (qualifications.isEmpty()) {
            model.addAttribute("message", "There is no qualification yet");
        }
        model.addAttribute("qualifications", qualifications);
        return "/qualification/qualification-show-list";
    }

    //add
    @GetMapping("/qualification-add")
    public String qualificationShowAddForm(Model model) {
        model.addAttribute("qualificationDto", new QualificationDto());
        return "/qualification/qualification-add";
    }

    @PostMapping("/qualification-add")
    public String qualificationAdd(@Valid @ModelAttribute(name = "qualificationDto") QualificationDto qualificationDto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        //
        new QualificationDto().validate(qualificationDto, bindingResult);

        //check if bindingResult has error
        if (bindingResult.hasErrors()) {
            return "/qualification/qualification-add";
        }

        //check if qualification name is already exist
        String newQualificationName = qualificationDto.getName();
        boolean isExisted = this.qualificationService.isExist(newQualificationName);
        String message = null;
        if (isExisted) {
            message = "Tên hạng bằng lái đã tồn tại trong " +
                    "hệ thống, vui lòng kiểm tra lại!";
            redirectAttributes.addFlashAttribute("message", message);
            return "/qualification/qualification-add";
        }

        //if nothing wrong, add new qualification
        Qualification qualification = new Qualification();
        BeanUtils.copyProperties(qualificationDto, qualification);
        this.qualificationService.add(qualification);
        return "redirect:/admin/qualification";
    }

    //remove
    @GetMapping("/remove/{qualificationId}")
    public String remove(@PathVariable(name = "qualificationId") Long qualificationId,
                         Model model) {
        Boolean removeSuccess = this.qualificationService.remove(qualificationId);
        if (removeSuccess) {
            model.addAttribute("message", "Removed qualification");
        } else {
            model.addAttribute("message", "Something wrong. Can't not found this qualification");
        }
        return "redirect:/admin/qualification";
    }
}