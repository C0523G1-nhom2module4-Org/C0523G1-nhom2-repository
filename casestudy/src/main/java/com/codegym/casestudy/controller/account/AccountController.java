package com.codegym.casestudy.controller.account;

import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("")
    public String showAccount(@RequestParam(defaultValue = "0", required = false) int page,
                              @RequestParam(defaultValue = "") String keyword,
                              Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("email").ascending());
        Page<IAccountDto> accountDtoPage = accountService.searchByEmail(pageable, keyword);
        model.addAttribute("accountDtoPage", accountDtoPage);
        model.addAttribute("keyword", keyword);
        return "/account/list";
    }

    @GetMapping("/login")
    public String showLogin(Model model, @RequestParam(name = "account", required = false) Account account) {
        model.addAttribute("account", account);
        return "login";
    }

    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("account", new Account());
        return "/account_signup";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam int deleteId, RedirectAttributes redirectAttributes) {
        Account account = accountService.findById(deleteId);
        accountService.deleteAccount(account);
        redirectAttributes.addFlashAttribute("msg", "Xóa Thành Công");
        return "redirect:/account";
    }

    @PostMapping("/create")
    public String signup(@ModelAttribute Account account, @RequestParam String repeat,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        Account newAccount = accountService.findByEmail(account.getEmail());
        if (newAccount != null) {
            model.addAttribute("msg", "Email Đã Được Đăng Kí");
            return "/account_signup";
        } else if (account.getPassword().equals(repeat)) {
            account.setDelete(true);
            account.setCreateDate(String.valueOf(LocalDate.now()));
            accountService.addAccount(account);
            redirectAttributes.addFlashAttribute("msg", "Đăng Kí Thành Công");
            return "redirect:/account/login";
        }
        model.addAttribute("msg", "Xác Thực Mật Khẩu Không Hợp Lệ !");
        return "account_signup";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutPage(Model model) {
        return "redirect:/";
    }

    @GetMapping("/change-password")
    public String showFormChangePass() {
        return "/change_password";
    }

    @PostMapping("/change_password")
    public String changePassword(Model model,
                                 Authentication authentication,
                                 @RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String repeatPassword,
                                 RedirectAttributes redirectAttributes) {
        String email = authentication.getName();
        Account account = accountService.findByEmail(email);
        if (!accountService.testPass(email, currentPassword)) {
            model.addAttribute("msg", "Mật khẩu cũ không đúng");
            return "/change_password";
        } else if (!Objects.equals(newPassword, repeatPassword)) {
            model.addAttribute("msg", "Xác nhận mật khẩu không đúng");
            return "/change_password";
        }
        accountService.changePass(email, newPassword);
        model.addAttribute("account", account);
        redirectAttributes.addFlashAttribute("msg", "Đổi thành công");
        return "redirect:/success";
    }

    // Thien
    //back to mainpage
    @GetMapping("/back")
    public String backToMainPage() {
        return "redirect:/";
    }
}
