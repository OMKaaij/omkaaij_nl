package com.omkaaij.omkaaij_nl.controller;

import com.omkaaij.omkaaij_nl.data.dto.RegistrationDTO;
import com.omkaaij.omkaaij_nl.data.dto.VisitorRegistrationDTO;
import com.omkaaij.omkaaij_nl.model.Person;
import com.omkaaij.omkaaij_nl.service.VisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j(topic = "VisitorRegistration")
public class RegistrationController {

    private final VisitorService visitorService;

    public RegistrationController (VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/registratie")
    public String registrationHandler (Model model) {
        model.addAttribute("hasErrors", "false");
        model.addAttribute("visitorRegistrationDTO", new VisitorRegistrationDTO());
        return "pages/registration";
    }

    @PostMapping("/registratie")
    public String registrationPostHandler (
            @Valid @ModelAttribute("VisitorRegistrationDTO") RegistrationDTO registrationDTO,
                                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString());
            model.addAttribute("hasErrors", "true");
            return "pages/confirmation";
        }
        model.addAttribute("hasErrors", "false");
        model.addAttribute("VisitorRegistrationDTO", registrationDTO);
        return "pages/confirmation";
    }

    @PostMapping("/bevestiging-registratie")
    public String confirmationPostHandler(
            @Valid @ModelAttribute("VisitorRegistrationDTO") VisitorRegistrationDTO visitorRegistrationDTO,
            Model model, RedirectAttributes redirectAttributes) {
     try {
         visitorService.registerVisitor(visitorRegistrationDTO, Person.class);
         redirectAttributes.addFlashAttribute("accountCreated", "true");
         return "redirect:/";
     } catch (UnexpectedRollbackException e) {
         log.error("Failed to register visitor. Rolled back transaction.");
         model.addAttribute("hasErrors", "true");
         return "pages/confirmation";
        }
    }


}
