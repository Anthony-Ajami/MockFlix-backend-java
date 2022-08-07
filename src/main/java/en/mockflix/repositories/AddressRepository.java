package en.mockflix.repositories;

import en.mockflix.services.AddressJPADAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository extends AddressJPADAO {

    public AddressRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
