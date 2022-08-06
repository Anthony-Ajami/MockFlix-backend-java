package en.mockflix.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SEEN_MOVIE")
public class SeenMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="ID")
    private Long id;

    @Column(name = "DATE")
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SeenMovie{" +
                "date=" + date +
                ", movie=" + movie +
                ", user=" + user +
                '}';
    }
}
