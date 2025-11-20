package com.sayra.umai.service.impl;

import com.sayra.umai.model.dto.AuthorDTO;
import com.sayra.umai.model.request.AuthorRequest;
import com.sayra.umai.model.dto.WorkDTO;
import com.sayra.umai.model.entity.work.Author;
import com.sayra.umai.repo_service.AuthorDataService;
import com.sayra.umai.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDataService authorDataService;


    public AuthorServiceImpl(AuthorDataService authorDataService) {
        this.authorDataService = authorDataService;
    }


  @Transactional(readOnly = true)
  @Override
  public List<AuthorDTO> getAllAuthors() {

    List<Author> authors = authorDataService.findAllWithWorks();

    return authors.stream()
      .map(author -> {

        List<WorkDTO> worksDTO = author.getWorks().stream()
          .map(work -> new WorkDTO(
            work.getId(),
            work.getTitle(),
            work.getDescription()
          ))
          .collect(Collectors.toList());

        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBio(author.getBio());
        dto.setDateOfBirth(author.getDate());
        dto.setWiki(author.getWiki());
        dto.setWorks(worksDTO);

        return dto;
      })
      .collect(Collectors.toList());
  }


  @Transactional
  @Override
  public Author save(AuthorRequest authorRequest) {
        if(authorRequest.getName() == null || authorRequest.getName().equals("")){
            throw new IllegalArgumentException("Author name is required");
        }
        if(authorDataService.existsByName(authorRequest.getName())){
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
        authorDataService.save(author);
        return author;
    }

}
