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
    private AuthenticationService authenticationService;

    @Autowired
    IBookService bookService;

    @RequestMapping(path = {"/books/list", "/books"}, method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return this.authenticationService.checkSessionBeforeRedirect("books/list");
    }

    @RequestMapping(path = "/books/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("bookModel", new Book());
        return this.authenticationService.checkSessionBeforeRedirect("books/add");
    }

    @RequestMapping(path = "/books/add", method =  RequestMethod.POST)
    public String add(@ModelAttribute Book book) {
        // for default: book is avaible
        book.setStatus(1);
        this.bookService.create(book);
        return this.authenticationService.checkSessionBeforeRedirect("redirect:/books");
    }
}
