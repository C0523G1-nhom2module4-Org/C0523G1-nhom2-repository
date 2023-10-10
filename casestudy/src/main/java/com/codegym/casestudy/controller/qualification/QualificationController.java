package com.codegym.casestudy.controller.qualification;

import com.codegym.casestudy.dto.qualification.QualificationDto;
import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.service.qualification.IQualificationService;
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

@Controller
@RequestMapping("/admin")
public class QualificationController {
    @Autowired
    private IQualificationService qualificationService;

    //show list
    @GetMapping("/qualification")
    public String qualificationPage(@RequestParam(defaultValue = "0", required = false) Integer page,
                                    @RequestParam(defaultValue = "", required = false) String searchName,
                                    Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("qualification_name").ascending());
        Page<Qualification> qualifications = this.qualificationService.findAllQualification(pageable, searchName);

        if (qualifications.isEmpty()) {
            model.addAttribute("message", "Hiện tại chưa có hạng mục bằng lái nào");
        }
        model.addAttribute("page", page);
        model.addAttribute("searchName", searchName);
        model.addAttribute("qualifications", qualifications);
        return "qualification-show-list";
    }

    //back - action cancel
    @GetMapping("/qualification/back")
    public String back() {
        return "redirect:/admin/qualification";
    }

    //add
    @GetMapping("/qualification-add")
    public String qualificationShowAddForm(Model model) {
        model.addAttribute("qualificationDto", new QualificationDto());
        System.out.println("--------------get method----------");
        return "/qualification-add";
    }

    @PostMapping("/qualification-add")
    public String qualificationAdd(@Valid @ModelAttribute QualificationDto qualificationDto,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        //
        new QualificationDto().validate(qualificationDto, bindingResult);

        //check if bindingResult has error
        if (bindingResult.hasErrors()) {
            return "qualification-add";
        }

        //check if qualification name is already exist
        String newQualificationName = qualificationDto.getName();
        boolean isExisted = this.qualificationService.isExist(newQualificationName);
        String isExistedMessage = null;
        if (isExisted) {
            isExistedMessage = "Tên hạng bằng lái đã tồn tại trong " +
                    "hệ thống, vui lòng kiểm tra lại!";
            model.addAttribute("isExistedMessage", isExistedMessage);
            return "qualification-add";
        }

        //if nothing wrong, add new qualification
        Qualification qualification = new Qualification();
        BeanUtils.copyProperties(qualificationDto, qualification);
        String success = "Thêm thành công bằng lái";
        redirectAttributes.addFlashAttribute("success",success);
        this.qualificationService.add(qualification);
        return "redirect:/admin/qualification";
    }

    //remove
    @GetMapping("/qualification/remove")
    public String remove(@RequestParam(name = "deleteId") Long deleteId,
                         RedirectAttributes redirectAttributes) {
        Qualification qualification = this.qualificationService.findById(deleteId);
        boolean removeSuccess = this.qualificationService.remove(qualification.getId());
        System.out.println("--------------remove get method----------");
        if (removeSuccess) {
            redirectAttributes.addFlashAttribute("success", "Đã xóa bằng lái");
        } else {
            redirectAttributes.addFlashAttribute("message", "Không thể tìm thấy thông tin về bằng " +
                    "lái này.");
        }
        return "redirect:/admin/qualification";
    }

    //edit
    @GetMapping("/qualification/edit/{qualificationId}")
    public String showEditForm(@PathVariable(name = "qualificationId") Long qualificationId,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        QualificationDto qualificationDto = new QualificationDto();
        Qualification existedQualification = null;
        try {
             existedQualification = this.qualificationService.findById(qualificationId);
        } catch (NullPointerException npe) {
            npe.getMessage();
        }
        if (existedQualification != null) {
            BeanUtils.copyProperties(existedQualification, qualificationDto);
            model.addAttribute("qualificationDto", qualificationDto);
            model.addAttribute("id", qualificationId);
            return "/qualification-edit";
        } else {
            redirectAttributes.addFlashAttribute("message", "Lỗi: Đối tượng không còn tồn tại");
            return "redirect:/admin/qualification";
        }
    }

    //chỉnh sửa: chưa bắt được trường hợp nếu để học phí có giá trị là âm
    @PostMapping("/qualification/edit")
    public String editQualification(@Valid @ModelAttribute(name = "qualificationDto") QualificationDto qualificationDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes
                                    ) {

        new QualificationDto().validate(qualificationDto, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("qualificationDto", qualificationDto);
            redirectAttributes.addFlashAttribute("message", "Có lỗi xảy ra khi sửa thông tin");
            return "redirect:/admin/qualification/edit";
        }

        String editSuccessMessage = null;
        Qualification qualification = new Qualification();
        BeanUtils.copyProperties(qualificationDto, qualification);
        this.qualificationService.add(qualification);
        editSuccessMessage = "Sửa đổi thông tin bằng lái: "
                + qualification.getName() + " thành công";
        String success = "Sửa thành công bằng lái " +qualification.getName();
        redirectAttributes.addFlashAttribute("success",success);
        redirectAttributes.addFlashAttribute("editSuccessMessage", editSuccessMessage);
        return "redirect:/admin/qualification";
    }
}