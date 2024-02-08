package org.sanetro.library.services;

import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;

import java.util.List;
import java.util.Optional;


public interface IBorrowedService {
    List<Borrower> getAll();

    void create(Borrower borrower);

    public void bookOrderProcess(User loggedUser, Book book);
}

