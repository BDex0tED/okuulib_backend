package com.sayra.umai.repo_service.impl;

import com.sayra.umai.model.entity.work.Author;
import com.sayra.umai.repo.AuthorRepo;
import com.sayra.umai.repo_service.AuthorDataService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorDataServiceImpl implements AuthorDataService {
  private final AuthorRepo authorRepo;

  public AuthorDataServiceImpl(AuthorRepo authorRepo) {
    this.authorRepo = authorRepo;
  }

  @Override
  public Author findByNameOrThrow(String name) {
    return authorRepo.findByName(name).orElseThrow(() -> new EntityNotFoundException("Author with name " + name + " not found"));
  }
  @Override
  public Author save(Author author) {
    return authorRepo.save(author);
  }

  @Override
  public boolean existsByName(String name){
    return authorRepo.existsByName(name);
  }

//  @Override
//  public Author update(Author author) {
//    return authorRepo.save(author);
//  }

  @Override
  public void delete(Long id) {
    if(authorRepo.existsById(id)){
      authorRepo.deleteById(id);
    }else{
      throw new EntityNotFoundException("Author with id " + id + " not found");
    }
  }

  @Override
  public Author findByIdOrThrow(Long id) {
    return authorRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Author with id " + id + " not found"));
  }

  @Override
  public Iterable<Author> findAll() {
    return authorRepo.findAll();
  }

  @Override
  public List<Author> findAllWithWorks() {
    if(authorRepo.count() == 0){throw new EntityNotFoundException("No Author found");}
    if(authorRepo.findAllWithWorks().isEmpty()){throw new EntityNotFoundException("No Author with works found");}
    return authorRepo.findAllWithWorks();
  }

}
