package org.sanetro.library.dao;

import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;

import java.util.List;
import java.util.Optional;

public interface IBorrowedDAO {
    List<Borrower> getAll();
    void delete(int id);
    void update(Borrower borrower);
    void create(Borrower borrower);
    List<Borrower> getByPattern(String pattern);
    void bookOrderProcess(User loggedUser, Book book);
}
