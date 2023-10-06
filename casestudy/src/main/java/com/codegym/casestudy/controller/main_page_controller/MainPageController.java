package com.codegym.casestudy.controller.main_page_controller;

import com.codegym.casestudy.model.qualification.Qualification;
import com.codegym.casestudy.service.qualification.IQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {
    @Autowired
    private IQualificationService qualificationService;
    @GetMapping()
    public String index() {
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
    public String contact() {
        return "/contact";
    }

    @GetMapping("/errors")
    public String error() {
        return "error";
    }

    @GetMapping("/qualification")
    public String qualification() {
        return "/qualification-add";
    }
    @GetMapping("/success")
    public String showLandingPage() {
        return "/landing_page";
    }
}
