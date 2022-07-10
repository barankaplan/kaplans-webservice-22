package com.spring.project01.kaplanjpahibernate.converter;

import com.spring.project01.kaplanjpahibernate.data.entity.Director;
import com.spring.project01.kaplanjpahibernate.dto.DirectorDTO;
import org.springframework.stereotype.Component;

@Component
public class DirectorConverter {
    public DirectorDTO toDirectorDTO(Director director)
    {
        var directorDTO = new DirectorDTO();

        directorDTO.setName(director.getName());
        directorDTO.setBirthDate(director.getBirthDate());

        return directorDTO;
    }

    public Director toDirector(DirectorDTO directorDTO)
    {
        var director = new Director();

        director.setName(directorDTO.getName());
        director.setBirthDate(directorDTO.getBirthDate());

        return director;
    }
}
