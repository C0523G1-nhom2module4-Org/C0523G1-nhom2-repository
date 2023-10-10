package com.codegym.casestudy.controller.contact_email;

import com.codegym.casestudy.model.contact_email.ContactEmail;
import com.codegym.casestudy.service.contact_email.IContactEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/contact-email")
public class AdminContactEmailController {

    @Autowired
    private IContactEmailService contactEmailService;
    @GetMapping
    public String showContactEmail(@RequestParam(defaultValue = "0", required = false) int page,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id"));
        Page<ContactEmail> contactEmails = this.contactEmailService.getGuessEmailList(pageable);
        String message = "Hiện tại chưa có phản hồi nào từ khách hàng";
        model.addAttribute("contactEmails", contactEmails);
        model.addAttribute("message", message);
        return "contact-mail-list";
    }

    @GetMapping("/detail/{contactEmailId}")
    public String showEmailDetail(@PathVariable(name = "contactEmailId") int id, Model model) {
        ContactEmail contactEmail = this.contactEmailService.findById(id);
        model.addAttribute("contactEmail",contactEmail);
        return "contact-mail-detail";
    }

    @GetMapping("/back")
    public String back() {
        return "redirect:/admin/contact-email";
    }

    @GetMapping("/send-ads-mail")
    public String showFormSendAdsMail() {
        return "contact-mail-send-ads";
    }

    @GetMapping("/do-send-ads-mail")
    public String doSendMail(@RequestParam("subject") String subject,
                             @RequestParam("content") String content,
                             RedirectAttributes redirectAttributes) {
        this.contactEmailService.sendAdsEmail(subject, content);
        int mailListSize = this.contactEmailService.findContactEmailsByEmailAddress().size();
        String message = "Đã gửi mail thành công cho " + mailListSize + " email!";
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/admin/contact-email/do-send-ads-mail";
    }
}
