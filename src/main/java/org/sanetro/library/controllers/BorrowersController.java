package org.sanetro.library.controllers;

import jakarta.annotation.Resource;
import org.sanetro.library.model.Book;
import org.sanetro.library.services.IBookService;
import org.sanetro.library.services.IBorrowedService;
import org.sanetro.library.services.impl.AuthenticationService;
import org.sanetro.library.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BorrowersController {

    @Resource
    SessionObject sessionObject;

    @Resource
    private AuthenticationService authenticationService;

    @Autowired
    IBorrowedService borrowedService;

    @RequestMapping(path = {"/borrowed/list", "/borrowed"}, method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("borrowers", this.borrowedService.getAll());
        return this.authenticationService.checkSessionBeforeRedirect("borrowed/list");
    }

}
