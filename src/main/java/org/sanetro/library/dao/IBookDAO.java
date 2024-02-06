package org.sanetro.library.dao;

import org.sanetro.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    Optional<Book> getById(int id);
    List<Book> getAll();
    void delete(int id);
    void update(Book book);
    List<Book> getByPattern(String pattern);
}
