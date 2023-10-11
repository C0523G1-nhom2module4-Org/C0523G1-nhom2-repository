package com.codegym.casestudy.controller.main_page_controller;

import com.codegym.casestudy.dto.contact_email_dto.ContactEmailDto;
import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.model.contact_email.ContactEmail;
import com.codegym.casestudy.model.contact_email.GuessEmail;
import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.service.account.IAccountService;
import com.codegym.casestudy.service.qualification.IQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {
    @Autowired
    private IQualificationService qualificationService;
    @Autowired
    private IAccountService iAccountService;
    @GetMapping()
    public String index(Model model) {
        GuessEmail guessEmail = new GuessEmail();
        model.addAttribute("guessEmail",guessEmail);

        return "/index";
    }

    @GetMapping("/feature")
    public String feature(Model model) {
        List<Qualification> qualificationList = this.qualificationService.findAll();

        model.addAttribute("qualificationList", qualificationList);

        return "/feature";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactEmail", new ContactEmailDto());
        return "/contact";
    }

    @GetMapping("/errors")
    public String error() {
        return "errors";
    }

    @GetMapping("/qualification")
    public String qualification() {
        return "/qualification-add";
    }
//    @GetMapping("/success")
//    public String showLandingPage(Model model) {
//        model.addAttribute("msg", "Đăng Nhập Thành Công");
//        return "/landing_page";
//    }
    @GetMapping("/success")
    public String showLandingPage(Model model, Principal principal) {
        Account name =  iAccountService.findByEmail(principal.getName());
        model.addAttribute("msg", "Đăng Nhập Thành Công");
        model.addAttribute("name", name.getEmail());
        return "/landing_page";
    }
}
