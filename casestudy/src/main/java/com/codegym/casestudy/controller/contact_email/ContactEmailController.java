package com.codegym.casestudy.controller.contact_email;

import com.codegym.casestudy.dto.contact_email_dto.ContactEmailDto;
import com.codegym.casestudy.model.contact_email.ContactEmail;
import com.codegym.casestudy.service.contact_email.IContactEmailService;
import com.codegym.casestudy.service.contact_email.IGuessEmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/main-page/contact-email")
public class ContactEmailController {
    @Autowired
    private IContactEmailService contactEmailService;

    @Autowired
    private IGuessEmailService guessEmailService;


    //add
    @GetMapping("/add")
    public String addContactEmail(@ModelAttribute(name = "contactEmail") ContactEmailDto contactEmailDto,
                                  RedirectAttributes redirectAttributes) {
        ContactEmail contactEmail = new ContactEmail();
        BeanUtils.copyProperties(contactEmailDto, contactEmail);
        this.contactEmailService.add(contactEmail);
        guessEmailService.sendResponse(contactEmail.getEmailAddress());
        redirectAttributes.addFlashAttribute("success","Cảm ơn bạn đã để lại thông tin phản hồi!");
        return "redirect:/contact";
    }

}
