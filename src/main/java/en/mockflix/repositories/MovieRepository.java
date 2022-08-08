package en.mockflix.repositories;

import en.mockflix.services.MovieJPADAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository extends MovieJPADAO {

    public MovieRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
