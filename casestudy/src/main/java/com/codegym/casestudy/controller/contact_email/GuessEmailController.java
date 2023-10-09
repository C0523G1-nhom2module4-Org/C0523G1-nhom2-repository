package com.codegym.casestudy.controller.contact_email;

import com.codegym.casestudy.model.contact_email.ContactEmail;
import com.codegym.casestudy.model.contact_email.GuessEmail;
import com.codegym.casestudy.service.contact_email.IContactEmailService;
import com.codegym.casestudy.service.contact_email.IGuessEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main-page/guess-email")
public class GuessEmailController {
    @Autowired
    private IGuessEmailService guessEmailService;

    @GetMapping("/add")
    public String addContactEmail(@RequestParam(name = "newGuessEmailAddress") String newGuessEmailAddress) {
        boolean flag = this.guessEmailService.findByEmail(newGuessEmailAddress);
        System.out.println("________________________________________" + newGuessEmailAddress);
        if (flag == false) {
            GuessEmail guessEmail = new GuessEmail(newGuessEmailAddress);
            this.guessEmailService.add(guessEmail);
            this.guessEmailService.sendResponse(newGuessEmailAddress);
        }
        return "redirect:/";
    }
}
