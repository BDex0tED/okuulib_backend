package com.sayra.umai.service;

import com.sayra.umai.model.dto.GenreDTO;
import com.sayra.umai.model.entity.work.Genre;

import java.util.List;

public interface GenreService {
  List<GenreDTO> getAllGenre();
  Genre getGenreById(Long genreId);
  Genre createGenre(String genreName);
  void deleteGenre(Long genreId);

}
