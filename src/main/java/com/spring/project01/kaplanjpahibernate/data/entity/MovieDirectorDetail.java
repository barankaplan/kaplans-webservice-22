
package com.spring.project01.kaplanjpahibernate.data.entity;



//@Embeddable
public class MovieDirectorDetail {
//    @Embedded
    private Movie m_movie;
//    @Embedded
    private Director m_director;

    public Director getM_director() {
        return m_director;
    }

    public Movie getM_movie() {
        return m_movie;
    }

    public MovieDirectorDetail(Movie movie, Director director)
    {
        m_movie = movie;
        m_director = director;
    }


    public Movie getMovie()
    {
        return m_movie;
    }

    public void setMovie(Movie movie)
    {
        m_movie = movie;
    }

    public Director getDirector()
    {
        return m_director;
    }

    public void setDirector(Director director)
    {
        m_director = director;
    }
}
