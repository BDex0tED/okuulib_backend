package com.sayra.umai.service;

import com.sayra.umai.model.entity.dto.AuthorDTO;
import com.sayra.umai.model.request.AuthorRequest;
import com.sayra.umai.model.entity.dto.WorkDTO;
import com.sayra.umai.model.entity.work.Author;
import com.sayra.umai.repo.AuthorRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepo authorRepo;


    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Transactional(readOnly = true)
    public Set<AuthorDTO> getAllAuthors() {

        Set<Author> authors = authorRepo.findAllWithWorks();

        return authors.stream()
                .map(author -> {

                    Set<WorkDTO> worksDTO = author.getWorks().stream()
                            .map(work -> new WorkDTO(
                                    work.getId(),
                                    work.getTitle(),
                                    work.getDescription()
                            ))
                            .collect(Collectors.toSet());

                    AuthorDTO authorDTO = new AuthorDTO();
                    authorDTO.setId(author.getId());
                    authorDTO.setName(author.getName());
                    authorDTO.setBio(author.getBio());

                    authorDTO.setDateOfBirth(author.getDate());
                    authorDTO.setWiki(author.getWiki());
                    authorDTO.setWorks(worksDTO);

                    return authorDTO;
                })
                .collect(Collectors.toSet());
    }

    public Author save(AuthorRequest authorRequest) {
        if(authorRequest.getName() == null || authorRequest.getName().equals("")){
            throw new IllegalArgumentException("Author name is required");
        }
        if(authorRepo.findByName(authorRequest.getName()).isPresent()){
            throw new IllegalArgumentException("Author with name: "+ authorRequest.getName()+" already exists");
        }
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setBio(authorRequest.getBio());
        author.setWiki(authorRequest.getWiki());
        author.setDate(authorRequest.getDateOfBirth());
        author.setPhoto(authorRequest.getPhoto());
//        author.setPhoto(authorInDTO.getPhoto()); should be in db then the url
//        author.setWorks(); need to be done
        authorRepo.save(author);
        return author;


    }

}
