package com.sayra.umai.controller;

import com.sayra.umai.model.request.BookmarkRequest;
import com.sayra.umai.service.impl.BookmarkServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkServiceImpl bookmarkService;
    public BookmarkController(BookmarkServiceImpl bookmarkService) {
        this.bookmarkService = bookmarkService;
    }
    @PostMapping("/create")
    public ResponseEntity<BookmarkRequest> createBookmark(@RequestBody BookmarkRequest bookmarkRequest, Principal principal) throws UserPrincipalNotFoundException {
        return ResponseEntity.ok(bookmarkService.createBookmark(bookmarkRequest, principal));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Iterable<BookmarkRequest>> getAllBookmarks(Principal principal) throws UserPrincipalNotFoundException, IllegalArgumentException {
        return ResponseEntity.ok(bookmarkService.getAllBookmarks(principal));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookmarkRequest> getBookmark(@PathVariable Long id, Principal principal) throws UserPrincipalNotFoundException, IllegalArgumentException {
        return ResponseEntity.ok(bookmarkService.getBookmark(id, principal));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookmark(@PathVariable Long id, Principal principal) throws UserPrincipalNotFoundException, IllegalArgumentException {
        bookmarkService.deleteBookmark(id, principal);
        return ResponseEntity.ok("Bookmark deleted successfully");
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllBookmarks(Principal principal) throws UserPrincipalNotFoundException, IllegalArgumentException {
        bookmarkService.deleteAllBookmarks(principal);
        return ResponseEntity.ok("All bookmarks deleted successfully");
    }



}
