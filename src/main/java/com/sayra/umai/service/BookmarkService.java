package com.sayra.umai.service;

import com.sayra.umai.model.request.BookmarkRequest;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.List;

public interface BookmarkService {
    BookmarkRequest createBookmark(BookmarkRequest bookmarkRequest, Principal principal) throws UserPrincipalNotFoundException;
    List<BookmarkRequest> getAllBookmarks(Principal principal) throws UserPrincipalNotFoundException;
    BookmarkRequest getBookmark(Long id, Principal principal) throws UserPrincipalNotFoundException;
    void deleteBookmark(Long id, Principal principal) throws UserPrincipalNotFoundException;
    void deleteAllBookmarks(Principal principal) throws UserPrincipalNotFoundException;
}
