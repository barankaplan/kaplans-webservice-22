package com.spring.project01.kaplanjpahibernate.data.repository;

import com.spring.project01.kaplanjpahibernate.data.entity.Movie;
import com.spring.project01.kaplanjpahibernate.data.entity.MovieDirectorDetail;

import org.csystem.util.data.repository.ICrudRepository;

public interface IMovieRepository extends ICrudRepository<Movie, Long> {
    Iterable<Movie> findMoviesByYear(int year);
    Iterable<MovieDirectorDetail> findMoviesDetailsByYear(int year);

    String addMovieDetailsByName(String id);

    Iterable<Movie> findMoviesByMonthYear(int month, int year);
}
