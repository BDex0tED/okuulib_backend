package com.sayra.umai.repo_service.impl;

import com.sayra.umai.exception.ResourceNotFoundException;
import com.sayra.umai.exception.UserNotFoundException;
import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.model.entity.work.Bookmark;
import com.sayra.umai.repo.BookmarkRepo;
import com.sayra.umai.repo_service.BookmarkDataService;
import com.sayra.umai.repo_service.UserEntityDataService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkDataServiceImpl implements BookmarkDataService {
    private final BookmarkRepo bookmarkRepo;
    private final UserEntityDataService userEntityDataService;

    public BookmarkDataServiceImpl(BookmarkRepo bookmarkRepo,
                                   UserEntityDataService userEntityDataService){
        this.bookmarkRepo=bookmarkRepo;
        this.userEntityDataService=userEntityDataService;
    }

    @Override
    public List<Bookmark> findAllByUserOrThrow(UserEntity user) {
        if(userEntityDataService.existsByUsernameOrThrow(user.getUsername())){
            return bookmarkRepo.findAllByUser(user);
        }
        throw new UserNotFoundException("User: " + user.getUsername() + " not found");
    }

    @Override
    public Bookmark findByIdAndUserOrThrow(Long id, UserEntity user) {
        if(userEntityDataService.existsByUsernameOrThrow(user.getUsername()) && id != null){
            return bookmarkRepo.findByIdAndUser(id, user).orElseThrow(()-> new ResourceNotFoundException("Bookmark with id: " + id + " not found"));
        } else {
            throw new ResourceNotFoundException("Bookmark not found. Id: "+ id);
        }
    }

    @Override
    public void deleteAllByUserOrThrow(UserEntity user) {
        if(userEntityDataService.existsByUsernameOrThrow(user.getUsername())){
            bookmarkRepo.deleteAllByUser(user);
        }
    }

    @Override
    public void save(Bookmark bookmark) {
        if(bookmark != null){
            bookmarkRepo.save(bookmark);
        }
    }

    @Override
    public void delete(Bookmark bookmark) {
        if(bookmark != null){
            bookmarkRepo.delete(bookmark);
        }
    }
}
