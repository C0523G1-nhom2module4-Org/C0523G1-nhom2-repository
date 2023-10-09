package com.codegym.casestudy.controller.contract;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.dto.contract.ContractDto;
import com.codegym.casestudy.model.contract.Contract;
import com.codegym.casestudy.model.student.Student;
import com.codegym.casestudy.service.contract.IContractService;
import com.codegym.casestudy.service.qualification.IQualificationService;
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


@Controller
@RequestMapping("/admin")
public class ContractController {
    @Autowired
    private IContractService contractService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IQualificationService qualificationService;


    @GetMapping("/contracts")
    public String showListContract(@RequestParam(defaultValue = "0", required = false) int page,
                                   @RequestParam(defaultValue = "", required = false) String nameSearch,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Contract> contractDtoPage = contractService.findAllBySearch(pageable, nameSearch);
        model.addAttribute("contractDtoPage", contractDtoPage);
        model.addAttribute("nameSearch", nameSearch);
        return "contract/list";
    }


    //    @GetMapping("/create/{studentId}/{qualificationId}")
//    public String showFormCreate(Model model,
//                                 @PathVariable int studentId,
//                                 @PathVariable long qualificationId) {
//        ContractDto contractDto = new ContractDto();
//        Student student = studentService.findById(studentId);
//        contractDto.setStudent(student);
//        contractDto.setQualification(qualificationService.findById(qualificationId));
//        contractDto.setDate(String.valueOf(LocalDate.now()));
//        model.addAttribute("contractDto", contractDto);
//        model.addAttribute("student", studentService.findById(studentId));
//        model.addAttribute("qualification", qualificationService.findById(qualificationId));
//        return "contract/create";
//    }
    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("contractDto", new ContractDto());
        return "contract/create";
    }

    @PostMapping("/create")
    public String saveFormCreate(@Valid @ModelAttribute ContractDto contractDto,
                                 Model model,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contract/create";
        }
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDto, contract);
//        contract.setStudent(studentService.findById(1));
        contractService.save(contract);


        model.addAttribute("contract", contract);
        return "contract/form";
//        contractService.save(contract);
//        redirectAttributes.addFlashAttribute("mess", "Created Success!!");
//        return "redirect:/contracts";
    }

    @PostMapping("/confirm")
    public String payContract(@ModelAttribute("contract") Contract contract,
                              RedirectAttributes redirectAttributes) {
        try {
            contractService.save(contract);
            redirectAttributes.addFlashAttribute("mess", "Bạn đã đăng ký khóa học thành công!!");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("mess", "Có lỗi xảy ra, vui lòng thử lại hoặc " +
                    "liên hệ QTV để được giải đáp!!!");
        }
        return "redirect:/admin/contracts";
    }
}
