package com.sayra.umai.repo_service;

import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.model.entity.work.Bookmark;

import java.util.List;
import java.util.Optional;

public interface BookmarkDataService {
    List<Bookmark> findAllByUserOrThrow(UserEntity user);
    Bookmark findByIdAndUserOrThrow(Long id, UserEntity user);
    void deleteAllByUserOrThrow(UserEntity user);
    void save(Bookmark bookmark);
    void delete(Bookmark bookmark);


}
