package org.sanetro.library.controllers;

import jakarta.annotation.Resource;
import org.sanetro.library.model.User;
import org.sanetro.library.services.IAuthenticationService;
import org.sanetro.library.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userModel", new User());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "login";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        this.authenticationService.login(user.getLogin(), user.getPassword());
        if(this.sessionObject.isLogged()) {
            return "redirect:/panel";
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/";
    }
}
