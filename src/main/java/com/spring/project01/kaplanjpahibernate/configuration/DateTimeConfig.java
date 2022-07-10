package com.spring.project01.kaplanjpahibernate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeConfig {
    @Bean
    public DateTimeFormatter getDateTimeFormatter()
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
}
