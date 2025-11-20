package com.sayra.umai.repo_service;

import com.sayra.umai.model.entity.work.Genre;

public interface GenreDataService {
  Genre findByNameOrThrow(String name);
  Genre findByIdOrThrow(Long id);
  boolean existsById(Long id);
  void deleteById(Long id);
//    Optional<Set<Genre>> findGenreByIds(Set<Long> ids);
}
