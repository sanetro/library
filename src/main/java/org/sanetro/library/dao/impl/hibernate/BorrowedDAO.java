package org.sanetro.library.dao.impl.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sanetro.library.dao.IBorrowedDAO;

import org.sanetro.library.model.Book;
import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public class BorrowedDAO implements IBorrowedDAO {

    private final String GET_BY_ID = "FROM org.sanetro.library.model.Borrower WHERE id = :id";
    private final String GET_ALL = "FROM org.sanetro.library.model.Borrower";
    private final String GET_BY_PATTERN = "FROM org.sanetro.library.model.Borrower WHERE user.login like :pattern OR book.title like :pattern";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Borrower> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Borrower> query = session.createQuery(GET_ALL, Borrower.class);
        List<Borrower> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new Borrower(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Borrower book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void create(Borrower borrower) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(borrower);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Borrower> getByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Borrower> query = session.createQuery(GET_BY_PATTERN, Borrower.class);
        query.setParameter("pattern", "%" + pattern + "%");
        List<Borrower> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void bookOrderProcess(User user, Book book) {
        Borrower borrower = new Borrower();
        borrower.setBook(book);
        borrower.setUser(user);
        borrower.setBegin(LocalDateTime.now());
        borrower.setDeadline(LocalDateTime.now().plusWeeks(2));
        borrower.setReturned(null);
        create(borrower);
    }
}
