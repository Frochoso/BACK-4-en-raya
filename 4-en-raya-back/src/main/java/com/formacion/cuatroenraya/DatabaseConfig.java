package com.formacion.cuatroenraya;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig {

    PropertyReader reader = new PropertyReader("src/main/resources/application.properties");

    private String host = reader.getProperty("db.host");

    private String database = reader.getProperty("db.database");

    private String username = reader.getProperty("db.username");

    private String password = reader.getProperty("db.password");

    private Integer port = Integer.parseInt(reader.getProperty("db.port"));

    PostgresqlConnectionFactory connectionFactory = new PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                    .host(host)
                    .database(database)
                    .username(username)
                    .password(password)
                    .port(port)
                    .build());

    R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);
}

