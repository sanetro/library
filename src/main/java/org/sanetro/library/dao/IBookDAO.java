package org.sanetro.library.dao;

import org.sanetro.library.model.Book;

import java.util.List;

public interface IBookDAO {
    Book getBook(int id);
    List<Book> getAll();
    void update(Book book);
    void create(Book book);
    List<Book> getByPattern(String pattern);
}
