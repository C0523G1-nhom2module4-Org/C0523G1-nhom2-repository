package com.codegym.casestudy.controller.account;

import com.codegym.casestudy.dto.account.AccountDto;
import com.codegym.casestudy.dto.account.IAccountDto;
import com.codegym.casestudy.model.account.Account;
import com.codegym.casestudy.model.role.Role;
import com.codegym.casestudy.model.user_role.UserRole;
import com.codegym.casestudy.service.account.IAccountService;
import com.codegym.casestudy.service.role.IRoleService;
import com.codegym.casestudy.service.user_role.IUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("")
    public String showAccount(@RequestParam(defaultValue = "0", required = false) int page,
                              @RequestParam(defaultValue = "") String keyword,
                              Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("email").ascending());
        Page<IAccountDto> accountDtoPage = accountService.searchByEmail(pageable, keyword);
        System.out.println("-------------------" + accountDtoPage);
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
        model.addAttribute("account", new AccountDto());
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

    @GetMapping("/forgot")
    public String showForgot(Model model) {
        return "/forgotPassword";
    }

    @PostMapping("/forgot")
    public String retrievalPassword(@RequestParam String email, Model model) {
        Account account = accountService.findByEmail(email);
        if (account != null) {
            String code = accountService.sendEmailAndReturnCode(email);
            model.addAttribute("code", code);
            model.addAttribute("email", email);
            model.addAttribute("msg", "Gửi Thành Công Hãy Kiểm Tra Email !");
            return "/confirm_password";
        } else
            model.addAttribute("msg", "Tài Khoản Không Tồn Tại");
        return "/forgotPassword";
    }

    @GetMapping("/confirm")
    public String showFormConfirm() {
        return "confirm_password";
    }

    @PostMapping("/confirm-code")
    public String confirmPassword(Model model,
                                  @RequestParam("code") String code,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("rePassword") String rePassword,
                                  @RequestParam("codeEmail") String codeEmail,
                                  @RequestParam("email") String email,
                                  RedirectAttributes redirectAttributes) {
        if (newPassword.equals(rePassword)) {
            if (code.equals(codeEmail)) {
                Account account = accountService.findByEmail(email);
                account.setPassword(newPassword);
                accountService.forgotPassword(account);
                model.addAttribute("msg", "Đổi mật khẩu thành công vui lòng đăng nhập");
                return "/login";
            } else {
                redirectAttributes.addFlashAttribute("newPassword", newPassword);
                redirectAttributes.addFlashAttribute("rePassword", rePassword);
                redirectAttributes.addFlashAttribute("codeEmail", codeEmail);
                redirectAttributes.addFlashAttribute("code", code);
                redirectAttributes.addFlashAttribute("msg", "vui lòng kiểm tra lại mã");
                return "redirect:/account/confirm";
            }
        } else {
            redirectAttributes.addFlashAttribute("newPassword", newPassword);
            redirectAttributes.addFlashAttribute("rePassword", rePassword);
            redirectAttributes.addFlashAttribute("codeEmail", codeEmail);
            redirectAttributes.addFlashAttribute("code", code);
            redirectAttributes.addFlashAttribute("msg", "vui lòng kiểm tra lại mật khẩu mới");
            return "redirect:/account/confirm";
        }
    }

    @GetMapping("/add")
    public String showFormAddAccount(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "/add";
    }

    @PostMapping("/add")
    public String addAccount(@Valid @ModelAttribute AccountDto accountDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("rePassword") String repeatPass,
                             @RequestParam("role") int role) {
        new AccountDto().validate(accountDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/add";
        }
        if (accountService.findByEmail(accountDto.getEmail())!=null){
            redirectAttributes.addFlashAttribute("msg","Email đã được đăng kí");
            return "redirect:/account/add";
        }
        if(!accountDto.getPassword().equals(repeatPass)){
        redirectAttributes.addFlashAttribute("msg","Xác Thực Mật Khẩu Không Hợp Lệ");
        return "redirect:/account/add";
        }
        Account newAccount = new Account();
        BeanUtils.copyProperties(accountDto,newAccount);
        accountDto.setCreateDate(String.valueOf(LocalDate.now()));
        accountService.createAccount(newAccount,role);
        redirectAttributes.addFlashAttribute("msg","Thêm Mới Thành Công");
        return "redirect:/account";
    }
    @GetMapping("/edit/{id}")
    public String showFormRole(@PathVariable int id, Model model){
        List<UserRole> userRoleList = userRoleService.findAll();
        Account account = accountService.findById(id);
        model.addAttribute("userRoleList",userRoleList);
        model.addAttribute("account",account);
        return "/edit";
    }
    @PostMapping("/edit")
    public String changeRole(@Valid @ModelAttribute Account account,
                             RedirectAttributes redirectAttributes){
    account.setCreateDate(String.valueOf(LocalDate.now()));
    accountService.editRole(account.getId(),account);
    redirectAttributes.addFlashAttribute("msg","Chỉnh Sửa Thành Công");
    return "redirect:/account";
    }

}
