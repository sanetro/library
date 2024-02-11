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
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    IBookService bookService;

    @RequestMapping(path = {"/borrowed/list", "/borrowed"}, method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("borrowers", this.borrowedService.getAll());
        return this.authenticationService.checkSessionBeforeRedirect("borrowed/list");
    }

    @RequestMapping(path = "/borrowed/overdue", method = RequestMethod.GET)
    public String overdue(Model model) {
        model.addAttribute("borrowers", this.borrowedService.overdue());
        return this.authenticationService.checkSessionBeforeRedirect("borrowed/list");
    }

    @RequestMapping(path = "/borrowed/actual", method = RequestMethod.GET)
    public String actual(Model model) {
        model.addAttribute("borrowers", this.borrowedService.actualBorrowers());
        return this.authenticationService.checkSessionBeforeRedirect("borrowed/list");
    }

    @RequestMapping(path = {"/borrowed/owned"}, method = RequestMethod.GET)
    public String owned(Model model) {
        model.addAttribute("borrowers", this.borrowedService.notReturnedBooksByUser(
                this.sessionObject.getLoggedUser()
        ));
        return this.authenticationService.checkSessionBeforeRedirect("borrowed/mylist");
    }

    @RequestMapping(path = "/borrowed/owned/{bookId}/{borrowerId}", method = RequestMethod.GET)
    public String confirmation(@PathVariable int bookId, @PathVariable int borrowerId) {
        Book book = this.bookService.getBook(bookId);
        book.setStatus(1);
        this.bookService.update(book);
        this.borrowedService.bookReturnProcess(
                this.sessionObject,
                this.borrowedService.getBorrower(borrowerId),
                this.sessionObject.getLoggedUser()
        );
        return this.authenticationService.checkSessionBeforeRedirect("redirect:/borrowed/owned");
    }

}
