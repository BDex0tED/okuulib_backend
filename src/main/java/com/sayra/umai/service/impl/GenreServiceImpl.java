package com.sayra.umai.service.impl;

import com.sayra.umai.model.entity.dto.GenreDTO;
import com.sayra.umai.model.entity.work.Genre;
import com.sayra.umai.repo.GenreRepo;
import com.sayra.umai.service.GenreService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepo genreRepo;
    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    public List<GenreDTO> getAllGenre() {
        List<GenreDTO> allGenres = new ArrayList<>();
        for (Genre genre : genreRepo.findAll()) {
            GenreDTO genreDTO = new GenreDTO();
            genreDTO.setId(genre.getId());
            genreDTO.setName(genre.getName());
            allGenres.add(genreDTO);
        }

        return allGenres;
    }
    public Genre getGenreById(Long genreId) {
      return genreRepo.findById(genreId).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public Genre createGenre(String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);
        genreRepo.save(genre);
        return genre;
    }
    @Transactional
    public void deleteGenre(Long genreId) {
        if(!genreRepo.existsById(genreId)){
            throw new EntityNotFoundException("Genre with id: "+genreId+" not found");
        }
        genreRepo.deleteById(genreId);
    }
    @Transactional
    public void fillDbWithGenres(){
        Set<String> genreNamesToAdd = new HashSet<>(Arrays.asList(
                "Эпос", "Роман", "Согуш", "Аңгеме", "Повесть", "Кыргыз классика","Тарыхый роман"
        ));
        Set<String> existingGenres = genreRepo.findAll().stream().map(Genre::getName).collect(Collectors.toSet());
        genreNamesToAdd.removeAll(existingGenres);

        genreNamesToAdd.stream().map(name->{
            Genre genre = new Genre();
            genre.setName(name);
            return genre;
        }).forEach(genreRepo::save);
        System.out.println("Database was filled with these genres: " +  genreNamesToAdd);
    }
}
