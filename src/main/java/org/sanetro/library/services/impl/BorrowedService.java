package org.sanetro.library.services.impl;

import org.sanetro.library.dao.IBorrowedDAO;
import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;
import org.sanetro.library.services.IBorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowedService implements IBorrowedService {

    @Autowired
    IBorrowedDAO borrowedDAO;

    @Override
    public List<Borrower> getAll() {
        return this.borrowedDAO.getAll();
    }

    @Override
    public void create(Borrower borrower) {
        this.borrowedDAO.create(borrower);
    }

    @Override
    public void bookOrderProcess(User loggedUser, Book book) { this.borrowedDAO.bookOrderProcess(loggedUser, book); }

}
