package com.spring.project01.kaplanjpahibernate.converter;

import com.spring.project01.kaplanjpahibernate.data.entity.Movie;
import com.spring.project01.kaplanjpahibernate.dto.MovieDTO;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MovieConverter {
    private final DateTimeFormatter m_dateTimeFormatter;

    public MovieConverter(DateTimeFormatter dateTimeFormatter)
    {
        m_dateTimeFormatter = dateTimeFormatter;
    }

    public MovieDTO toMovieDTO(Movie movie)
    {
        var movieDTO = new MovieDTO();

        movieDTO.setName(movie.getName());
        movieDTO.setSceneTime(m_dateTimeFormatter.format(movie.getSceneTime()));
        movieDTO.setRating(movie.getRating());
        movieDTO.setCost(movie.getCost());

        return movieDTO;
    }

    public Movie toMovie(MovieDTO movieDTO)
    {
        var movie = new Movie();

        movie.setName(movieDTO.getName());
        movie.setSceneTime(LocalDate.parse(movieDTO.getSceneTime(), m_dateTimeFormatter));
        movie.setRating(movieDTO.getRating());
        movie.setCost(movieDTO.getCost());

        return movie;
    }
}
