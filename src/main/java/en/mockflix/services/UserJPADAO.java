package en.mockflix.services;

import en.mockflix.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserJPADAO {

    private final SessionFactory factory;

    public UserJPADAO(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public void create(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }

    public void delete(User user) {
        Session session = getSession();
        session.delete(user);
    }

    public void update(User user) {
        Session session = getSession();
        session.update(user);
    }

    public User search(User user) {
        Session session = getSession();
        Query<User> query = getQuery(user, session);
        return query.uniqueResult();
    }

    private Query<User> getQuery(User user, Session session) {
        Query<User> query = session.createQuery("from User where id = :id");
        query.setParameter("id", user.getId());
        return query;
    }

    private Session getSession() {
        Session currentSession = null;
        try {
            currentSession = this.factory.getCurrentSession();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (currentSession != null && currentSession.isOpen()) {
            return currentSession;
        } else {
            return this.factory.openSession();
        }
    }
}
