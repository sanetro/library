package org.sanetro.library.controllers;

import jakarta.annotation.Resource;
import org.sanetro.library.model.Book;
import org.sanetro.library.model.User;
import org.sanetro.library.services.IBookService;
import org.sanetro.library.services.impl.AuthenticationService;
import org.sanetro.library.services.impl.BookService;
import org.sanetro.library.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BooksController {

    @Resource
    SessionObject sessionObject;

    @Resource
    AuthenticationService authenticationService;

    @Resource
    BookService bookService;

    @RequestMapping(path = {"/books/list", "/books"}, method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "books/list";
    }

    @RequestMapping(path = "/books/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("bookModel", new Book());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "books/add";
    }

    @RequestMapping(path = "/books/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Book book) {
        this.bookService.create(book);
        return "books/add";
    }
}
