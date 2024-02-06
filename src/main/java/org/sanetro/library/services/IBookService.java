package org.sanetro.library.services;

import org.sanetro.library.model.Book;

import java.util.List;


public interface IBookService {
    List<Book> getAll();
}

