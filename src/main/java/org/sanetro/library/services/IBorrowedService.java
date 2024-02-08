package org.sanetro.library.services;

import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;

import java.util.List;


public interface IBorrowedService {
    List<Borrower> getAll();

    void create(Borrower borrower);

    void bookOrderProcess(User loggedUser, Book book);

    List<Borrower> notReturnedBooksByUser(User user);

    void bookReturnProcess(User loggedUser, Book book, Borrower borrower);

    Borrower getBorrower(int borrowerId);
}

