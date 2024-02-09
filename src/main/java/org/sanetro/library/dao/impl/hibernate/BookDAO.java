package org.sanetro.library.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sanetro.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sanetro.library.dao.IBookDAO;

import java.util.List;

@Repository
public class BookDAO implements IBookDAO {

    private final String GET_BY_ID = "FROM org.sanetro.library.model.Book WHERE id = :id";
    private final String GET_ALL = "FROM org.sanetro.library.model.Book";
    private final String GET_BY_PATTERN = "FROM org.sanetro.library.model.Book WHERE author like :pattern OR title like :pattern OR isbn like :pattern";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Book getBook(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_ID, Book.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_ALL, Book.class);
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new Book(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Book book) {
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
    public void create(Book book) {
        book.setStatus(1); // default: active book to order
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_PATTERN, Book.class);
        query.setParameter("pattern", "%" + pattern + "%");
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }
}
