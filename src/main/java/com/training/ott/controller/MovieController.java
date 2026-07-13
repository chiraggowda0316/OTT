package com.training.ott.controller;

import com.training.ott.model.Movie;
import com.training.ott.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @PostConstruct
    public void initData() {
        if (movieRepository.count() == 0) {
            Movie m1 = new Movie(); m1.setTitle("TOXIC"); m1.setGenre("Action"); m1.setRequiredPlan("PREMIUM");
            Movie m2 = new Movie(); m2.setTitle("Kirathaka"); m2.setGenre("Romanace"); m2.setRequiredPlan("BASIC");
            Movie m3 = new Movie(); m3.setTitle("Kalasipalya"); m3.setGenre("Action"); m3.setRequiredPlan("FREE");
            movieRepository.saveAll(List.of(m1, m2, m3));
        }
    }

    @GetMapping("/ping")
    public String ping() {
        return "{\"status\":\"UP\"}";
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}

