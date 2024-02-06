package org.sanetro.library.services.impl;

import org.sanetro.library.dao.IBookDAO;
import org.sanetro.library.model.Book;
import org.sanetro.library.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }
}
