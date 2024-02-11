package org.sanetro.library.services.impl;

import org.sanetro.library.dao.IBookDAO;
import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Override
    public void create(Book book) {
        this.bookDAO.create(book);
    }

    @Override
    public Book getBook(int id) {
        return this.bookDAO.getBook(id);
    }

    @Override
    public void update(Book book) {
        this.bookDAO.update(book);
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.bookDAO.getByPattern(pattern);
    }
}
