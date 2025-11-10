package com.sayra.umai.repo;

import com.sayra.umai.model.entity.work.Chapter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepo extends CrudRepository<Chapter, Long > {
}
