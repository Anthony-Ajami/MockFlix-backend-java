package en.mockflix.services;

import en.mockflix.entities.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AddressJPADAO {

    private final SessionFactory factory;

    public AddressJPADAO(SessionFactory factory) {
        this.factory = factory;
    }

    public List<Address> getAllAddresses() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Address");
        List<Address> addresses = query.list();
        tx.commit();
        session.close();
        return addresses;
    }

    public Address addAddress(Address address) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(address);
        transaction.commit();
        return address;
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
