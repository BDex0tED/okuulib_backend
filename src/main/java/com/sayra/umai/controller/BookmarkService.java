package com.sayra.umai.controller;

import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.repo.UserEntityRepo;
import com.sayra.umai.model.request.BookmarkRequest;
import com.sayra.umai.model.entity.work.Bookmark;
import com.sayra.umai.model.entity.work.Chapter;
import com.sayra.umai.model.entity.work.Work;
import com.sayra.umai.repo.BookmarkRepo;
import com.sayra.umai.repo.ChapterRepo;
import com.sayra.umai.repo.WorkRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;

import java.util.*;

@Service
public class BookmarkService {
    private final UserEntityRepo userRepo;
    private final WorkRepo workRepo;
    private final ChapterRepo chapterRepo;
    private final BookmarkRepo bookmarkRepo;
    public BookmarkService(BookmarkRepo bookmarkRepo, UserEntityRepo userRepo, WorkRepo workRepo, ChapterRepo chapterRepo) {
        this.bookmarkRepo = bookmarkRepo;
        this.userRepo = userRepo;
        this.workRepo = workRepo;
        this.chapterRepo = chapterRepo;
    }

    public BookmarkRequest createBookmark(BookmarkRequest bookmarkRequest, Principal principal) throws UserPrincipalNotFoundException {
        UserEntity user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UserPrincipalNotFoundException("User with id" + bookmarkRequest.getUserId() + " not found"));
        Work work = workRepo.findById(bookmarkRequest.getWorkId()).orElseThrow(()->new IllegalArgumentException("Work with id: "+ bookmarkRequest.getWorkId()+" not found"));
        Chapter chapter = chapterRepo.findById(bookmarkRequest.getChapterId()).orElseThrow(()->new IllegalArgumentException("Chapter with id: "+ bookmarkRequest.getChapterId()+" not found"));

        Bookmark bookmark = new Bookmark();
        bookmark.setWork(work);
        bookmark.setUser(user);
        bookmark.setChapter(chapter);
        bookmark.setChunkId(bookmarkRequest.getChunkId());
        bookmark.setUserNote(bookmarkRequest.getUserNote());
        bookmark.setWorkNote(bookmarkRequest.getWorkNote());
        bookmark.setStartOffset(bookmarkRequest.getStartOffset());
        bookmark.setEndOffset(bookmarkRequest.getEndOffset());

        bookmark.validateOffsets();

        bookmarkRepo.save(bookmark);

        return bookmarkRequest;
    }

    public List<BookmarkRequest> getAllBookmarks(Principal principal) throws UserPrincipalNotFoundException {
        UserEntity user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UserPrincipalNotFoundException("User with id" + principal.getName() + " not found"));
        List<Bookmark> bookmarks = bookmarkRepo.findAllByUser(user);
        if(bookmarks.isEmpty()){
            throw new EntityNotFoundException("User has no bookmarks");
        }
        List<BookmarkRequest> bookmarkRequests = new ArrayList<>();
        for(Bookmark bookmark : bookmarks){
            BookmarkRequest bookmarkRequest = getBookmarkInDTO(bookmark);
            bookmarkRequests.add(bookmarkRequest);
        }
        return bookmarkRequests;
    }

    public BookmarkRequest getBookmark(Long id, Principal principal) throws UserPrincipalNotFoundException {
        UserEntity user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UserPrincipalNotFoundException("User not found"));

        Bookmark bookmark = bookmarkRepo.findByIdAndUser(id, user).orElseThrow(()->new EntityNotFoundException("Bookmark with id: "+id+" not found"));

        return getBookmarkInDTO(bookmark);

    }

    public void deleteBookmark(Long id, Principal principal) throws UserPrincipalNotFoundException {
        UserEntity user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UserPrincipalNotFoundException("User not found"));
        Bookmark bookmark = bookmarkRepo.findByIdAndUser(id, user).orElseThrow(()->new EntityNotFoundException("Bookmark with id: "+id+" not found"));
        bookmarkRepo.delete(bookmark);
    }

    @Transactional
    public void deleteAllBookmarks(Principal principal) throws UserPrincipalNotFoundException {
        UserEntity user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UserPrincipalNotFoundException("User not found"));
        List<Bookmark> bookmarks = bookmarkRepo.findAllByUser(user);
        if(bookmarks.isEmpty()){
            throw new EntityNotFoundException("User has no bookmarks");
        }
        bookmarkRepo.deleteAll(bookmarks);
    }

    private static BookmarkRequest getBookmarkInDTO(Bookmark bookmark) {
        BookmarkRequest bookmarkRequest = new BookmarkRequest();
        bookmarkRequest.setWorkId(bookmark.getWork().getId());
        bookmarkRequest.setChapterId(bookmark.getChapter().getId());
        bookmarkRequest.setChunkId(bookmark.getChunkId());
        bookmarkRequest.setUserNote(bookmark.getUserNote());
        bookmarkRequest.setWorkNote(bookmark.getWorkNote());
        bookmarkRequest.setStartOffset(bookmark.getStartOffset());
        bookmarkRequest.setEndOffset(bookmark.getEndOffset());
        return bookmarkRequest;
    }

}
