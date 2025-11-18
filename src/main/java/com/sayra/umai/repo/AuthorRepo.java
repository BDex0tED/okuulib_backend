package com.sayra.umai.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sayra.umai.model.entity.work.Author;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

  @Query("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.works")
  List<Author> findAllWithWorks();

  boolean existsByName(String name);
}
