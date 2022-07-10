package com.spring.project01.kaplanjpahibernate.data.dal;

import com.spring.project01.kaplanjpahibernate.data.entity.Director;
import com.spring.project01.kaplanjpahibernate.data.entity.Movie;
import com.spring.project01.kaplanjpahibernate.data.entity.MovieDirectorDetail;
import com.spring.project01.kaplanjpahibernate.data.repository.IDirectorRepository;
import com.spring.project01.kaplanjpahibernate.data.repository.IMovieRepository;

import org.springframework.stereotype.Component;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;

@Component
public class MovieServiceApplicationDAL {
    private final IMovieRepository m_movieRepository;
    private final IDirectorRepository m_directorRepository;

    public MovieServiceApplicationDAL(IMovieRepository movieRepository, IDirectorRepository directorRepository)
    {
        m_movieRepository = movieRepository;
        m_directorRepository = directorRepository;
    }

    public long countMovies()
    {
        return doWorkForRepository(m_movieRepository::count, "MovieServiceApplicationDAL.countMovies");
    }

    public Iterable<Movie> findAllMovies()
    {
        return doWorkForRepository(m_movieRepository::findAll, "MovieServiceApplicationDAL.findAllMovies");
    }

    public Iterable<Movie> findMoviesByMonthYear(int month, int year)
    {
        return doWorkForRepository(() -> m_movieRepository.findMoviesByMonthYear(month, year),
                "MovieServiceApplicationDAL.findMoviesByMonthYear");
    }

    public Iterable<Movie> findMoviesByYear(int year)
    {
        return doWorkForRepository(() -> m_movieRepository.findMoviesByYear(year), "MovieServiceApplicationDAL.findByYear");
    }

    public Iterable<MovieDirectorDetail> findMoviesDetailsByYear(int year)
    {
        return doWorkForRepository(() -> m_movieRepository.findMoviesDetailsByYear(year),
                "MovieServiceApplicationDAL.findMoviesDetailsByYear");
    }

    public String addMovieDetailsByName(String name)
    {
        return doWorkForRepository(() -> m_movieRepository.addMovieDetailsByName(name),
                "MovieServiceApplicationDAL.findMoviesDetailsByYear");
    }


    public Movie saveMovie(Movie movie)
    {
        return doWorkForRepository(() -> m_movieRepository.save(movie), "MovieServiceApplicationDAL.saveMovie");
    }

    public long countDirectors()
    {
        return doWorkForRepository(m_directorRepository::count, "MovieServiceApplicationDAL.countDirectors");
    }

    public Director saveDirector(Director director)
    {
        return doWorkForRepository(() -> m_directorRepository.save(director), "MovieServiceApplicationDAL.saveDirector");
    }

    public Iterable<Director> findAllDirectors()
    {
        return doWorkForRepository(m_directorRepository::findAll, "MovieServiceApplicationDAL.findAllDirectors");
    }

    //...
}
