package com.sayra.umai.controller;

import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.repo.UserEntityRepo;
import com.sayra.umai.model.entity.dto.ChatSessionDTO;
import com.sayra.umai.model.entity.work.ChatSession;
import com.sayra.umai.repo.ChatSessionRepo;
import com.sayra.umai.repo.MessageRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ChatSessionService {
    private final MessageRepo messageRepo;
    private final ChatSessionRepo chatSessionRepo;
    private final UserEntityRepo userEntityRepo;
    public ChatSessionService(MessageRepo messageRepo, ChatSessionRepo chatSessionRepo, UserEntityRepo userEntityRepo) {
        this.messageRepo = messageRepo;
        this.chatSessionRepo = chatSessionRepo;
        this.userEntityRepo = userEntityRepo;
    }

    public List<ChatSessionDTO> getUserSessions(Principal principal){
        UserEntity user = userEntityRepo.findByUsername(principal.getName()).orElseThrow(()->new IllegalArgumentException("User not found"));

        List<ChatSession> chatSessions = chatSessionRepo.findAllByUser(user);

        if(chatSessions.isEmpty()){
            throw new EntityNotFoundException("User has no chat sessions");
        }

        return chatSessions.stream().map(chatSession->{
            ChatSessionDTO chatSessionDTO = new ChatSessionDTO();
            chatSessionDTO.setId(chatSession.getId());
            chatSessionDTO.setTitle(chatSession.getTitle());
            chatSessionDTO.setUserName(user.getUsername());
            return chatSessionDTO;
        }).toList();
    }

//    public ChatSessionDTO getChatSession(Principal principal, Long id){
//        UserEntity user = userEntityRepo.findByUsername(principal.getName()).orElseThrow(()->new IllegalArgumentException("User not found"));
//        ChatSession chatSession = createOrContinueChatSession(principal, id);
//
//        ChatSessionDTO chatSessionDTO = new ChatSessionDTO();
//        chatSessionDTO.setUserName(chatSession.getUser().getUsername());
//
//    }


    public ChatSession createOrContinueChatSession(Principal principal, Long id){
        UserEntity user = userEntityRepo.findByUsername(principal.getName()).orElseThrow(()->new IllegalArgumentException("User not found"));
        if(id == null){
            ChatSession chatSession = new ChatSession();
            chatSession.setUser(user);
            chatSession.setTitle("New chat");
            chatSessionRepo.save(chatSession);
            return chatSession;
        }
        return chatSessionRepo.findByIdAndUser(id, user).orElseThrow(()->new EntityNotFoundException("Chat session not found"));
    }




}
