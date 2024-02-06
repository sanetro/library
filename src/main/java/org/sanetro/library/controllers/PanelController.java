package org.sanetro.library.controllers;

import jakarta.annotation.Resource;
import org.sanetro.library.services.impl.AuthenticationService;
import org.springframework.ui.Model;
import org.sanetro.library.session.SessionObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PanelController {

    @Resource
    SessionObject sessionObject;
    @Resource
    AuthenticationService authenticationService;

    @RequestMapping(path = "/panel", method = RequestMethod.GET)
    public String panel(Model model) {
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return authenticationService.checkSessionBeforeRedirect("panel");
    }
}
