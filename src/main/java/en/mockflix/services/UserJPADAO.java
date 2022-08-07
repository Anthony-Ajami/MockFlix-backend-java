package en.mockflix.services;

import en.mockflix.entities.Address;
import en.mockflix.entities.Contact;
import en.mockflix.entities.Role;
import en.mockflix.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserJPADAO {

    private final SessionFactory factory;

    public UserJPADAO(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public List<User> getAllUsers() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> users = query.list();
        tx.commit();
        session.close();
        return users;
    }

    public User addUserFinal(User user, Contact contact, Address address, Role role) {
        User user1 = new User();
        Contact contact1 = new Contact();
        contact1.setFirstName(contact.getFirstName());
        contact1.setLastName(contact.getLastName());
        contact1.setEmail(contact.getEmail());
        contact1.setPhoneNumber(contact.getPhoneNumber());
        contact1.setBillingAddress(address);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setRole(role);
        user1.setContact(contact1);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user1);
        transaction.commit();
        session.close();
        return user1;
    }

    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        return user;
    }

    public User addRole(String username, String roleName) {
        // get user by username
        User user = getUserByUsername(username);
        Role role = new Role();
        role.setName(roleName);

        user.setRole(role);

        // update found user
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return user;
    }

    public User addContact(String username, String email, String firstName, String lastName, String phoneNumber) {
        // get user by username
        User user = getUserByUsername(username);
        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);

        user.setContact(contact);

        // update found user
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return user;
    }

    public User addAddress(String username, String country, String area, String city, String street, String number) {
        // get user by username
        User user = getUserByUsername(username);
        Contact contact = user.getContact();
        Address address = new Address();

        address.setCountry(country);
        address.setArea(area);
        address.setCity(city);
        address.setStreet(street);
        address.setNumber(number);
        contact.setBillingAddress(address);
        user.setContact(contact);

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        session.update(contact);
        session.update(user);

        transaction.commit();
        session.close();
        return user;
    }

    public void deleteUserById(Long id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
    }

    public User getUserById(Long id) {
        Session session = getSession();
        Query<User> query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public User getUserByUsername(String username) {
        Session session = getSession();
        Query<User> query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    public User getUserByEmail(String email) {
        Session session = getSession();
        Query<User> query = session.createQuery("from User where contact.email = :email");
        query.setParameter("email", email);
        return query.uniqueResult();
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
