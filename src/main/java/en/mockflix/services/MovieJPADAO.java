package en.mockflix.services;

import en.mockflix.entities.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MovieJPADAO {

    private final SessionFactory factory;

    public MovieJPADAO(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public void addMovie(Movie movie) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(movie);
        transaction.commit();
    }

    public List<Movie> getAllMovies() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Movie");
        List<Movie> movies = query.list();
        tx.commit();
        session.close();
        return movies;
    }

    public List<Movie> getAllActionMovies() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Movie where genre = 'Action'");
        List<Movie> movies = query.list();
        tx.commit();
        session.close();
        return movies;
    }

    public List<Movie> getAllComedyMovies() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Movie where genre = 'Comedy'");
        List<Movie> movies = query.list();
        tx.commit();
        session.close();
        return movies;
    }

    public List<Movie> getAllDocumentaryMovies() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Movie where genre = 'Documentary'");
        List<Movie> movies = query.list();
        tx.commit();
        session.close();
        return movies;
    }

    public List<Movie> getAllHorrorMovies() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Movie where genre = 'Horror'");
        List<Movie> movies = query.list();
        tx.commit();
        session.close();
        return movies;
    }

    public List<Movie> getAllRomanceMovies() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Movie where genre = 'Romance'");
        List<Movie> movies = query.list();
        tx.commit();
        session.close();
        return movies;
    }

    public List<Movie> getAllTopRatedMovies() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Movie where genre = 'Top Rated'");
        List<Movie> movies = query.list();
        tx.commit();
        session.close();
        return movies;
    }

    public List<Movie> getAllTrendingMovies() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Movie where genre = 'Trending'");
        List<Movie> movies = query.list();
        tx.commit();
        session.close();
        return movies;
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
