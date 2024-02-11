package org.sanetro.library.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.sanetro.library.model.Book;
import org.sanetro.library.services.IBookService;
import org.sanetro.library.services.IBorrowedService;
import org.sanetro.library.services.impl.AuthenticationService;
import org.sanetro.library.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    IBorrowedService borrowedService;

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

    @RequestMapping(path = "/books/search-book", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(Model model, @ModelAttribute("pattern") String pattern, HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            model.addAttribute("pattern", "");
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            model.addAttribute("borrowers",
                    this.borrowedService.GetAllBooksWithStatusAndUser(
                            this.bookService.getByPattern(pattern),
                            this.bookService.getAll()
                    ));
        }
        return this.authenticationService.checkSessionBeforeRedirect("books/search");
    }

    @RequestMapping(path = "/books/confirm/{id}", method = RequestMethod.GET)
    public String confirmation(Model model, @PathVariable int id) {
        model.addAttribute("bookModel", this.bookService.getBook(id));
        return this.authenticationService.checkSessionBeforeRedirect("books/confirm");
    }

    @RequestMapping(path = "/books/confirm/{id}", method = RequestMethod.POST)
    public String confirmed(Model model, @PathVariable int id) {
        Book book = this.bookService.getBook(id);
        book.setStatus(0);
        this.bookService.update(book);
        this.borrowedService.bookOrderProcess(
                this.sessionObject.getLoggedUser(),
                this.bookService.getBook(id)
        );
        return this.authenticationService.checkSessionBeforeRedirect("redirect:/books");
    }

    @RequestMapping(path = "/books/add", method =  RequestMethod.POST)
    public String add(@ModelAttribute Book book) {
        this.bookService.create(book);
        return this.authenticationService.checkSessionBeforeRedirect("redirect:/books");
    }
}