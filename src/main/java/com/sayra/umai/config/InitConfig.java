package com.sayra.umai.config;

import com.sayra.umai.controller.GenreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {
    @Bean
    public CommandLineRunner fillDbWithGenres(GenreService genreService) {
        return args -> {
            genreService.fillDbWithGenres();
        };
    }
}
