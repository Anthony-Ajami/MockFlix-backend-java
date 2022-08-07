package en.mockflix.services;

import en.mockflix.entities.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ContactJPADAO {

    private final SessionFactory factory;

    public ContactJPADAO(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public List<Contact> getAllContacts() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Contact");
        List<Contact> contacts = query.list();
        tx.commit();
        session.close();
        return contacts;
    }

    public Contact addContact(String email, String firstName, String lastName, String phoneNumber) {
        // get user by email


        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(contact);
        transaction.commit();
        return contact;
    }

    // get contact by email
    public Contact getContactByEmail(String email) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Contact where email = :email");
        query.setParameter("email", email);
        Contact contact = (Contact) query.uniqueResult();
        transaction.commit();
        session.close();
        return contact;
    }

    private Session getSession() {
        Session currentSession = null;
        try {
            currentSession = this.factory.getCurrentSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (currentSession != null && currentSession.isOpen()) {
            return currentSession;
        } else {
            return this.factory.openSession();
        }
    }

}
