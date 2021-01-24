package com.omkaaij.omkaaij_nl.controller;

import com.omkaaij.omkaaij_nl.model.Visitor;
import com.omkaaij.omkaaij_nl.service.LoginService;
import com.omkaaij.omkaaij_nl.service.VisitorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes({"visitor", "nameCurrentVisitor"})
public class LoginController {

    private VisitorService visitorService;
    private LoginService loginService;

    public LoginController (VisitorService visitorService, LoginService loginService) {
        this.visitorService = visitorService;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "pages/login";
    }

    @PostMapping("/loggedin_user")
    public String handleLogin (@RequestParam(name = "userName") String userName, @RequestParam(name = "password")
                               String password, Model model) {
        Optional<Visitor> visitor = visitorService.getVisitorByUserName(userName);
        if(visitor.isPresent()) {
            Visitor visitorFound = visitor.get();
            if (loginService.validVisitor(visitorFound, password)) {
                model.addAttribute("visitor", visitorFound);
                model.addAttribute("nameCurrentVisitor", visitorService.printNameVisitor(visitorFound.getVisitorID()));
                return "pages/loggedin_user";
            }
        }
        return "pages/errorpage";
    }
}
