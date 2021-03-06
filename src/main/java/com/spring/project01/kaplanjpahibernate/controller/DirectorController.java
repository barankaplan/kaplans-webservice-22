package com.spring.project01.kaplanjpahibernate.controller;


import com.spring.project01.kaplanjpahibernate.dto.DirectorDTO;
import com.spring.project01.kaplanjpahibernate.service.DirectorService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.csystem.util.exception.ExceptionUtil.subscribe;

@RestController
@Scope("prototype")
@RequestMapping("api/director")
public class DirectorController {
    private final DirectorService m_directoryService;

    public DirectorController(DirectorService directoryService)
    {
        m_directoryService = directoryService;
    }

    @GetMapping("/all")
    public List<DirectorDTO> findAllDirectors()
    {
        return subscribe(m_directoryService::findAllDirectors, ex -> new ArrayList<>());
    }

    @PostMapping("/save")
    public DirectorDTO saveDirector(@RequestBody DirectorDTO directorDTO)
    {
        return m_directoryService.saveDirector(directorDTO);
    }
    //...
}
