package en.mockflix;

import en.mockflix.configuration.Configuration;
import en.mockflix.entities.Movie;
import en.mockflix.services.MovieJPADAO;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileReader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Configuration.class)
public class FillDBWithMovies {

    @Inject
    @Named("postgresSessionFactory")
    SessionFactory sf;

    // SHOULD DELETE ALL "MOVIES" TABLE ROWS BEFORE RUNNING THIS 'TEST'
    @Test
    public void jsonToMovieObjects() {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //for all json files
        String genreArray[] = {"trending", "top_rated", "romance", "horror", "documentary", "comedy", "action"};

        for (String genre : genreArray) {
            try(FileReader reader = new FileReader("src/main/resources/JSON-files/" + genre + ".json")) {
                //Read JSON file
                Object obj = jsonParser.parse(reader);

                JSONArray moviesList = (JSONArray) obj;

                moviesList.forEach(movie -> {
                    parseMovieObject((JSONObject) movie);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void parseMovieObject(JSONObject movie) {
        //Get movie object within list
        JSONObject movieObject = (JSONObject) movie.get("movie");

        String title = (String) movieObject.get("title");
        String overview = (String) movieObject.get("overview");
        String poster_path = (String) movieObject.get("poster_path");
        String release_date = (String) movieObject.get("release_date");
        String backdrop_path = (String) movieObject.get("backdrop_path");
        String genre = (String) movieObject.get("genre");

        System.out.println("Title: " + title);
        System.out.println("Overview: " + overview);
        System.out.println("Poster Path: " + poster_path);
        System.out.println("Release Date: " + release_date);
        System.out.println("Backdrop Path: " + backdrop_path);
        System.out.println("Genre: " + genre);

        Movie movie1 = new Movie();

        movie1.setTitle(title);
        movie1.setDescription(overview);
        movie1.setPosterPath(poster_path);
        movie1.setReleaseDate(release_date);
        movie1.setBackdropPath(backdrop_path);
        movie1.setGenre(genre);

        MovieJPADAO movieJPADAO = new MovieJPADAO(sf);
        movieJPADAO.addMovie(movie1);
    }

}
