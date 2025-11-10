package com.sayra.umai.controller.impl;

import com.sayra.umai.model.entity.dto.AllWorksDTO;
import com.sayra.umai.model.response.WorkResponse;

import java.util.List;

public interface WorkService {
    List<AllWorksDTO> getAllWorks();

    WorkResponse getWorkById(Long workId);

}
