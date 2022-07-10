package com.spring.project01.kaplanjpahibernate.runner;

import com.spring.project01.kaplanjpahibernate.data.entity.Movie;
import com.spring.project01.kaplanjpahibernate.data.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class MyRunner implements CommandLineRunner {
    private final IMovieRepository imovieRepository;

    public MyRunner(@Qualifier("movieRepository") IMovieRepository imovieRepository) {
        this.imovieRepository = imovieRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Movie movie= new Movie();
        movie.setName("Inception");
        movie.setSceneTime(LocalDate.now());
        movie.setRating(1000L);
        movie.setCost(BigDecimal.valueOf(500));
        movie.setImdb(8.6F);
        imovieRepository.save(movie);
    }
}
