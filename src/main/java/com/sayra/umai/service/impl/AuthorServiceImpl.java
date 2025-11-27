package com.sayra.umai.service.impl;

import com.sayra.umai.mapper.AuthorMapper;
import com.sayra.umai.model.dto.AuthorDTO;
import com.sayra.umai.model.entity.work.Work;
import com.sayra.umai.model.request.AuthorRequest;
import com.sayra.umai.model.entity.work.Author;
import com.sayra.umai.repo_service.AuthorDataService;
import com.sayra.umai.repo_service.WorkDataService;
import com.sayra.umai.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDataService authorDataService;
    private final WorkDataService workDataService;
    private final AuthorMapper authorMapper;


    public AuthorServiceImpl(AuthorDataService authorDataService,
                             AuthorMapper authorMapper,
                             WorkDataService workDataService) {
        this.authorDataService = authorDataService;
        this.authorMapper = authorMapper;
        this.workDataService = workDataService;
    }


  @Transactional(readOnly = true)
  @Override
  public List<AuthorDTO> getAllAuthors() {
    return authorMapper.toAuthorDTO(authorDataService.findAllWithWorks());
  }


  @Transactional
  @Override
  public AuthorDTO save(AuthorRequest authorRequest) {
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
        List<Work> authorWorks = workDataService.findAllById(authorRequest.getWorkIds());
        if(!authorWorks.isEmpty()){
            author.setWorks(authorWorks);
        }
        authorDataService.save(author);
      return authorMapper.toAuthorDTO(author);
  }

}
