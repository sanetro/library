package org.sanetro.library.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sanetro.library.dao.IBorrowedDAO;

import org.sanetro.library.model.Borrower;
import org.sanetro.library.model.User;
import org.sanetro.library.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BorrowedDAO implements IBorrowedDAO {

    private final String GET_BY_ID = "FROM org.sanetro.library.model.Borrower WHERE id = :id";
    private final String GET_ALL = "FROM org.sanetro.library.model.Borrower";
    private final String GET_BORROWED_BOOKS_BY_USER = "SELECT b FROM org.sanetro.library.model.Borrower b JOIN b.user u WHERE u.id = :id AND b.book.status = 0 AND b.returned IS NULL";
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    SessionObject sessionObject;

    @Override
    public List<Borrower> notReturnedBooksByUser(User user) {
        Session session = this.sessionFactory.openSession();
        try {
            Query<Borrower> query = session.createQuery(GET_BORROWED_BOOKS_BY_USER, Borrower.class);
            query.setParameter("id", user.getId());
            List<Borrower> result = query.getResultList();
            return result;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Borrower> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Borrower> query = session.createQuery(GET_ALL, Borrower.class);
        List<Borrower> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void update(Borrower borrower) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(borrower);
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
    public Borrower getBorrower(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Borrower> query = session.createQuery(GET_BY_ID, Borrower.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}
