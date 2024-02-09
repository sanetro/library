package org.sanetro.library.services.impl;

import org.sanetro.library.dao.IBorrowedDAO;
import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;
import org.sanetro.library.services.IBorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedService implements IBorrowedService {

    @Autowired
    IBorrowedDAO borrowedDAO;

    @Override
    public List<Borrower> getAll() {
        return this.borrowedDAO.getAll();
    }

    @Override
    public void create(Borrower borrower) {
        this.borrowedDAO.create(borrower);
    }

    @Override
    public void bookOrderProcess(User loggedUser, Book book) { this.borrowedDAO.bookOrderProcess(loggedUser, book); }

    @Override
    public List<Borrower> notReturnedBooksByUser(User user) { return this.borrowedDAO.notReturnedBooksByUser(user); }

    @Override
    public void bookReturnProcess(User loggedUser, Book book, Borrower borrower) { this.borrowedDAO.bookReturnProcess(loggedUser, book, borrower); }

    @Override
    public Borrower getBorrower(int borrowerId) { return this.borrowedDAO.getBorrower(borrowerId); }

    @Override
    public List<Borrower> GetAllBooksWithStatusAndUser(List<Book> books, List<Book> allBooks) { return this.borrowedDAO.GetAllBooksWithStatusAndUser(books, allBooks); }

    @Override
    public List<Borrower> overdue() { return this.borrowedDAO.overdue(); }

    @Override
    public List<Borrower> actualBorrowers() { return this.borrowedDAO.actualBorrowers(); }
}
