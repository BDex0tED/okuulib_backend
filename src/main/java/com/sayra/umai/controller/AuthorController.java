package com.sayra.umai.controller;

import com.sayra.umai.model.entity.dto.AuthorDTO;
import com.sayra.umai.model.request.AuthorRequest;
import com.sayra.umai.model.entity.work.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController("/author")
public class AuthorController {
    private AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping()
    public ResponseEntity<Set<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping("/create-author")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.save(authorRequest));
    }
}
