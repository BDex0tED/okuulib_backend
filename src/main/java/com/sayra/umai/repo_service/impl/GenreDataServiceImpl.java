package com.sayra.umai.repo_service.impl;

import com.sayra.umai.model.entity.work.Genre;
import com.sayra.umai.repo.GenreRepo;
import com.sayra.umai.repo_service.GenreDataService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreDataServiceImpl implements GenreDataService {
  private final GenreRepo genreRepo;

  public GenreDataServiceImpl(GenreRepo genreRepo) {
    this.genreRepo = genreRepo;
  }

  @Override
  public Genre findByNameOrThrow(String name) {
    if(name == null || name.length() <= 1 || name.length() > 100){
      throw new IllegalArgumentException("Invalid name");
    }
    return genreRepo.findByName(name).orElseThrow(()->new EntityNotFoundException("Genre with name " + name + " not found"));
  }

  @Override
  public Genre findByIdOrThrow(Long id) {
    return genreRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Genre with id " + id + " not found"));
  }

  @Override
  public boolean existsById(Long id) {
    if(id == null || id <= 0){
      throw new IllegalArgumentException("Invalid id");
    }
    return genreRepo.existsById(id);
  }

  @Override
  public void deleteById(Long id) {
    if(id == null || id <= 0){
      throw new IllegalArgumentException("Invalid id");
    }
    if(!genreRepo.existsById(id)){
      throw new EntityNotFoundException("Genre with id " + id + " not found");
    }
    genreRepo.deleteById(id);
  }
}
