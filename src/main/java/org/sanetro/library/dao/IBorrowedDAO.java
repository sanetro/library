package org.sanetro.library.dao;

import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;

import java.util.List;

public interface IBorrowedDAO {
    List<Borrower> getAll();
    void delete(int id);
    void update(Borrower borrower);
    void create(Borrower borrower);
    List<Borrower> getByPattern(String pattern);
    void bookOrderProcess(User loggedUser, Book book);

    List<Borrower> actualBorrowers();

    List<Borrower> notReturnedBooksByUser(User user);

    void bookReturnProcess(User loggedUser, Book book, Borrower borrower);

    Borrower getBorrower(int borrowerId);

    List<Borrower> GetAllBooksWithStatusAndUser(List<Book> books, List<Book> allBooks);

    List<Borrower> overdue();
}
