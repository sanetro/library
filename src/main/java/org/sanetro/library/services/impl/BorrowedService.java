package org.sanetro.library.services.impl;

import org.sanetro.library.dao.IBorrowedDAO;
import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;
import org.sanetro.library.services.IBorrowedService;
import org.sanetro.library.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public void bookOrderProcess(User loggedUser, Book book) {
        Borrower borrower = new Borrower();
        borrower.setBook(book);
        borrower.setUser(loggedUser);
        borrower.setBegin(LocalDateTime.now());
        borrower.setDeadline(LocalDateTime.now().plusWeeks(2));
        borrower.setReturned(null);
        create(borrower);
    }

    @Override
    public List<Borrower> notReturnedBooksByUser(User user) {
        return this.borrowedDAO.notReturnedBooksByUser(user);
    }

    @Override
    public void bookReturnProcess(SessionObject session, Borrower borrower, User user) {
        if(session.isLogged() && user.equals(session.getLoggedUser())) {
            borrower.setReturned(LocalDateTime.now());
            borrowedDAO.update(borrower);
        }
    }

    @Override
    public Borrower getBorrower(int borrowerId) {
        return this.borrowedDAO.getBorrower(borrowerId);
    }

    @Override
    public List<Borrower> GetAllBooksWithStatusAndUser(List<Book> books, List<Book> allBooks) {
        List<Borrower> result = new ArrayList<>();
        List<Borrower> allBorrower = getAll();
        Collections.sort(allBorrower, (borrower1, borrower2) -> borrower2.getBegin().compareTo(borrower1.getBegin()));
        for (Book book: books) {
            for (Borrower borrower: allBorrower) {
                if (book.equals(borrower.getBook())) {
                    result.add(borrower);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public List<Borrower> overdue() {
        return getAll().stream()
            .filter(borrower -> borrower.getDeadline().isBefore(LocalDateTime.now()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Borrower> actualBorrowers() {
        return getAll().stream()
                .filter(borrower -> borrower.getReturned() == null)
                .collect(Collectors.toList());
    }
}
