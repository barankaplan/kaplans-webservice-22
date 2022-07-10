package com.spring.project01.kaplanjpahibernate.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class AbstractContainerBasedTest {
    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER ;
    static {
        POSTGRE_SQL_CONTAINER= new PostgreSQLContainer("postgres:latest")
                .withUsername("kaplan").withPassword("1989").withDatabaseName("kaplan");

        POSTGRE_SQL_CONTAINER.start();
    }

//    @Container
//    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
//            .withUsername("kaplan").withPassword("1989").withDatabaseName("kaplan");

    @DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);

    }


}
