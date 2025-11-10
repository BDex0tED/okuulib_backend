package com.sayra.umai.repo;

import com.sayra.umai.model.entity.work.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepo  extends CrudRepository<Response, Long> {
}
