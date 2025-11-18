package com.sayra.umai.service;

import com.sayra.umai.model.entity.dto.AllWorksDTO;
import com.sayra.umai.model.entity.dto.GenreDTO;
import com.sayra.umai.model.entity.work.*;
import com.sayra.umai.model.dto.*; // Новые DTO
import com.sayra.umai.model.response.AuthorResponse;
import com.sayra.umai.model.response.ChapterResponse;
import com.sayra.umai.model.response.ChunkResponse;
import com.sayra.umai.model.response.WorkResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface WorkMapper {
  GenreDTO genreToGenreDTO(Genre genre);
  List<GenreDTO> genresToGenreDTOs(List<Genre> genres);
  Set<GenreDTO> genresSetToGenreDTOSet(Set<Genre> genres);

  AuthorResponse authorToAuthorResponse(Author author);

  @Mappings({
    @Mapping(source = "author.name", target = "authorName", defaultValue = "Unknown author"),
    @Mapping(source = "genres", target = "genres")
  })
  AllWorksDTO workToAllWorksDTO(Work work);

  List<AllWorksDTO> worksToAllWorksDTOs(List<Work> works);

  ChunkResponse chunkToChunkResponse(Chunk chunk);
  Set<ChunkResponse> chunksToChunkResponses(Set<Chunk> chunks);

  @Mappings({
    @Mapping(source = "chapterNumber", target = "chapterNumber"),
    @Mapping(source = "chapterTitle", target = "chapterTitle"),
    @Mapping(source = "chunks", target = "chunks")
  })
  ChapterResponse chapterToChapterResponse(Chapter chapter);
  Set<ChapterResponse> chaptersToChapterResponses(Set<Chapter> chapters);

  @Mappings({
    @Mapping(source = "author", target = "author"),
    @Mapping(source = "genres", target = "genres"),
    @Mapping(source = "chapters", target = "chapters"),
    @Mapping(target = "otherWorks", ignore = true)
  })
  WorkResponse workToWorkResponse(Work work);
}
