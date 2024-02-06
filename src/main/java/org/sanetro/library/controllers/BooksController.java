package org.sanetro.library.controllers;

import jakarta.annotation.Resource;
import org.sanetro.library.services.IBookService;
import org.sanetro.library.services.impl.AuthenticationService;
import org.sanetro.library.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BooksController {

    @Resource
    SessionObject sessionObject;

    @Resource
    AuthenticationService authenticationService;

    @Resource
    IBookService bookService;

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public String books(Model model) {
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return authenticationService.checkSessionBeforeRedirect("books/index");
    }

    @RequestMapping(path = "/books/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return authenticationService.checkSessionBeforeRedirect("books/list");
    }

    @RequestMapping(path = "/books/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return authenticationService.checkSessionBeforeRedirect("books/index");
    }
}
