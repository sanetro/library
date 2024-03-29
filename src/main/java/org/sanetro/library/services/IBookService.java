package org.sanetro.library.services;

import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;

import java.util.List;
import java.util.Optional;


public interface IBookService {
    List<Book> getAll();

    void create(Book book);

    Book getBook(int id);

    void update(Book book);

    List<Book> getByPattern(String pattern);

    void init();
}

