package en.mockflix.repositories;

import en.mockflix.services.ContactJPADAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository extends ContactJPADAO {

    public ContactRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
