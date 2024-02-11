package org.sanetro.library.services;

import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;
import org.sanetro.library.session.SessionObject;

import java.util.List;


public interface IBorrowedService {
    List<Borrower> getAll();

    void create(Borrower borrower);

    void bookOrderProcess(User loggedUser, Book book);

    List<Borrower> notReturnedBooksByUser(User user);

    void bookReturnProcess(SessionObject session, Borrower borrower, User user);

    Borrower getBorrower(int borrowerId);

    List<Borrower> GetAllBooksWithStatusAndUser(List<Book> books, List<Book> allBooks);

    List<Borrower>  overdue();

    List<Borrower> actualBorrowers();
}

