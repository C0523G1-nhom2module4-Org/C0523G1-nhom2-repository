package com.codegym.casestudy.controller.main_page_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainPageController {
    @GetMapping()
    public String index() {
        return "/index";
    }

    @GetMapping("/feature")
    public String feature() {
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
        return "/errors";
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
