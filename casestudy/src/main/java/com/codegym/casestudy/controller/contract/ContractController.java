package com.codegym.casestudy.controller.contract;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.dto.contract.ContractDto;
import com.codegym.casestudy.model.contract.Contract;
import com.codegym.casestudy.model.student.Student;
import com.codegym.casestudy.service.contract.IContractService;
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
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private IContractService contractService;


    @GetMapping("")
    public String showListContract(@RequestParam(defaultValue = "0",required = false) int page,
                              @RequestParam(defaultValue = "") String nameSearch,
                              @RequestParam(defaultValue = "") String sort,
                              @RequestParam(defaultValue = "") String condition,
                              Model model ){
        Pageable pageable = PageRequest.of(page,5 );
        Page<Contract> contractDtoPage =  contractService.findAllBySearch(pageable,nameSearch,sort,condition);
        model.addAttribute("contractDtoPage",contractDtoPage);
        model.addAttribute("nameSearch",nameSearch);
        model.addAttribute("sort",sort);
        model.addAttribute("condition",condition);
        return "contract/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("contractDto", new ContractDto());
        return "contract/create";
    }

    @PostMapping("/create")
    public String saveFormCreate(@Valid @ModelAttribute ContractDto contractDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contract/create";
        }
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDto, contract);
        contractService.save(contract);
        redirectAttributes.addFlashAttribute("mess", "Created Success!!");
        return "redirect:/contracts";
    }
}
