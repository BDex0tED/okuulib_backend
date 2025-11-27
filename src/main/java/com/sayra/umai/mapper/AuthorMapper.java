package com.sayra.umai.mapper;

import com.sayra.umai.model.dto.AuthorDTO;
import com.sayra.umai.model.entity.work.Author;
import com.sayra.umai.model.request.AuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper  INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(source = "date", target = "dateOfBirth")
    AuthorDTO toAuthorDTO(Author author);

    AuthorDTO toAuthorDTO(AuthorRequest authorRequest);

    List<AuthorDTO> toAuthorDTO(List<Author> authorList);

}
