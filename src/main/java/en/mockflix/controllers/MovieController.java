package en.mockflix.controllers;

import en.mockflix.entities.Movie;
import en.mockflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieRepository.getAllMovies();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies/action")
    public List<Movie> getAllActionMovies(){
        return movieRepository.getAllActionMovies();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies/comedy")
    public List<Movie> getAllComedyMovies(){
        return movieRepository.getAllComedyMovies();
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies/documentary")
    public List<Movie> getAllDocumentaryMovies(){
        return movieRepository.getAllDocumentaryMovies();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies/horror")
    public List<Movie> getAllHorrorMovies(){
        return movieRepository.getAllHorrorMovies();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies/romance")
    public List<Movie> getAllRomanceMovies(){
        return movieRepository.getAllRomanceMovies();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies/top-rated")
    public List<Movie> getAllTopRatedMovies(){
        return movieRepository.getAllTopRatedMovies();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies/trending")
    public List<Movie> getAllTrendingMovies(){
        return movieRepository.getAllTrendingMovies();
    }
}
