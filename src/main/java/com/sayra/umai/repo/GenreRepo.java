package com.sayra.umai.repo;

import com.sayra.umai.model.entity.work.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(String name);
    Optional<Genre> findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);

  boolean existsByName(String name);
//    Optional<Set<Genre>> findGenreByIds(Set<Long> ids);
}
