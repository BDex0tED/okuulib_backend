package com.sayra.umai.service.impl;

import com.sayra.umai.exception.ResourceNotFoundException;
import com.sayra.umai.mapper.ChatSessionMapper;
import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.model.request.ChatSessionRequest;
import com.sayra.umai.repo.UserEntityRepo;
import com.sayra.umai.model.dto.ChatSessionDTO;
import com.sayra.umai.model.entity.work.ChatSession;
import com.sayra.umai.repo.MessageRepo;
import com.sayra.umai.repo_service.ChatSessionDataService;
import com.sayra.umai.service.ChatSessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sayra.umai.exception.ValidationException;

import java.util.List;

@Service
public class ChatSessionServiceImpl implements ChatSessionService {
    private final MessageRepo messageRepo;
    private final ChatSessionDataService chatSessionDataService;
    private final UserService userService;
    private final UserEntityRepo userEntityRepo;
    private final ChatSessionMapper chatSessionMapper;
    public ChatSessionServiceImpl(MessageRepo messageRepo,
                                  ChatSessionDataService chatSessionDataService,
                                  UserEntityRepo userEntityRepo,
                                  ChatSessionMapper chatSessionMapper,
                                  UserService userService) {
        this.messageRepo = messageRepo;
        this.chatSessionDataService = chatSessionDataService;
        this.userEntityRepo = userEntityRepo;
        this.chatSessionMapper = chatSessionMapper;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChatSessionDTO> getUserSessions(){
        UserEntity user = userService.getCurrentUser();
        List<ChatSession> chatSessions = chatSessionDataService.findAllByUser(user);

        if(chatSessions.isEmpty()){
            throw new ResourceNotFoundException("User has no chat sessions");
        }

        return chatSessionMapper.toChatSessionDTO(chatSessions);
    }

    @Transactional(readOnly = true)
    @Override
    public ChatSessionDTO getChatSession(Long id){
        UserEntity user = userService.getCurrentUser();
        ChatSession chatSession = chatSessionDataService.findByIdAndUser(id, user).orElseThrow(()->new ResourceNotFoundException("ChatSession not found"));

        return chatSessionMapper.toChatSessionDTO(chatSession);
    }

    @Transactional
    @Override
    public ChatSessionDTO createSession(ChatSessionRequest chatSessionRequest){
        UserEntity user = userService.getCurrentUser();

        ChatSession chatSession = new ChatSession();
        chatSession.setTitle(chatSessionRequest.title());
        chatSession.setUser(user);

        return chatSessionMapper.toChatSessionDTO(chatSessionDataService.save(chatSession));
    }


    @Transactional
    @Override
    public void delete(Long id) {
        if(id == null){
            throw new ValidationException("Id must not be null");
        }
        chatSessionDataService.delete(chatSessionDataService.findByIdAndUser(id, userService.getCurrentUser()).orElseThrow(()->new ResourceNotFoundException("ChatSession not found")));
    }




}
