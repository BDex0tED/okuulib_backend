package com.sayra.umai.mapper;

import com.sayra.umai.model.dto.ChatSessionDTO;
import com.sayra.umai.model.entity.work.ChatSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatSessionMapper {
    ChatSessionMapper INSTANCE = Mappers.getMapper(ChatSessionMapper.class);

    @Mapping(source = "user.username", target="userName")
    ChatSessionDTO toChatSessionDTO(ChatSession chatSession);

    List<ChatSessionDTO> toChatSessionDTO(List<ChatSession> chatSessions);
}
