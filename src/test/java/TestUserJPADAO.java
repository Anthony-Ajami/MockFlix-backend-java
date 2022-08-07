import en.mockflix.configuration.Configuration;
import en.mockflix.entities.Address;
import en.mockflix.entities.Contact;
import en.mockflix.entities.Role;
import en.mockflix.entities.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import en.mockflix.services.UserJPADAO;

import javax.inject.Inject;
import javax.inject.Named;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Configuration.class)
public class TestUserJPADAO {

        @Inject
        @Named("postgresSessionFactory")
        SessionFactory sf;

        @Test
        public void test() {

                UserJPADAO userJPADAO = new UserJPADAO(sf);

                Contact contact = new Contact();
                contact.setFirstName("JOHN");
                contact.setLastName("DOE");
                contact.setEmail("anthony_ajami@hotmail.com");
                contact.setPhoneNumber("0123456789");

                Address address = new Address();
                address.setCountry("LEBANON");
                address.setArea("Ile de France");
                address.setCity("Paris 13");
                address.setStreet("rue Théroigne de Méricourt");
                address.setNumber("4");

                Role role = new Role();
                role.setName("USER_PREMIUM");

                User user = new User();
                user.setUsername("johndoe");
                user.setPassword("password");

                userJPADAO.addUserFinal(user, contact, address, role);
        }
}
