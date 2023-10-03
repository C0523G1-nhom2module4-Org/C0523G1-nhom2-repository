package com.codegym.casestudy.controller.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("")
    public String showAccount(@RequestParam(defaultValue = "0",required = false) int page,
                              @RequestParam(defaultValue = "") String keyword,
                              Model model ){
        Pageable pageable = PageRequest.of(page,5, Sort.by("email").ascending());
        Page<IAccountDto> accountDtoPage =  accountService.searchByEmail(pageable,keyword);
        model.addAttribute("accountDtoPage",accountDtoPage);
        model.addAttribute("keyword",keyword);
        return "account/list";
    }


}
