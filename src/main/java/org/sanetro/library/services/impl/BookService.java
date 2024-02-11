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

    @Override
    public void init() {
        String[] titles = {"Przygody w nieznanym", "Tajemnice kosmosu", "Odyseja czasu", "Zaginione królestwa", "Echo przeszłości"};
        String[] authors = {"Jan Kowalski", "Ewa Nowak", "Adam Zielinski", "Ola Malinowska", "Marek Wisniewski"};
        String[] isbns = {"123-4567-890A-1B", "234-5678-901C-2D", "345-6789-012E-3F", "456-7890-123G-4H", "567-8901-234I-5J"};

        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setTitle(titles[i]);
            book.setAuthor(authors[i]);
            book.setIsbn(isbns[i]);
            book.setStatus(1);
            this.bookDAO.create(book);
        }
    }
}
