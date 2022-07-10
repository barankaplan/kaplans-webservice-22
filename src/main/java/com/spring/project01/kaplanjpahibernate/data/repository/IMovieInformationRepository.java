package com.spring.project01.kaplanjpahibernate.data.repository;

import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.entity.MovieInformation;
import org.springframework.data.repository.CrudRepository;

public interface IMovieInformationRepository extends CrudRepository<MovieInformation, Long> {
    public MovieInformation findByInfoId (Long infoID);

}

