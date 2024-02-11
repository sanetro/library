package org.sanetro.library.dao;

import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;

import java.util.List;

public interface IBorrowedDAO {
    List<Borrower> getAll();
    void update(Borrower borrower);
    void create(Borrower borrower);
    List<Borrower> notReturnedBooksByUser(User user);
    Borrower getBorrower(int borrowerId);

}
