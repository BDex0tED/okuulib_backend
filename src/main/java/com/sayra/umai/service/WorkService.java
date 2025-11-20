package com.sayra.umai.service;

import com.sayra.umai.model.dto.AllWorksDTO;
import com.sayra.umai.model.response.WorkResponse;

import java.util.List;

public interface WorkService {
    List<AllWorksDTO> getAllWorks();

    WorkResponse findById(Long workId);

}
