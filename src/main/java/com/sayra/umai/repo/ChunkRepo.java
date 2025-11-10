package com.sayra.umai.repo;

import com.sayra.umai.model.entity.work.Chunk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChunkRepo extends CrudRepository<Chunk, Long> {
}
