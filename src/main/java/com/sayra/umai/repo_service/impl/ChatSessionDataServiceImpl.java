package com.sayra.umai.repo_service.impl;

import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.model.entity.work.ChatSession;
import com.sayra.umai.repo.ChatSessionRepo;
import com.sayra.umai.repo_service.ChatSessionDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatSessionDataServiceImpl implements ChatSessionDataService {
    private final ChatSessionRepo chatSessionRepo;
    public ChatSessionDataServiceImpl(ChatSessionRepo chatSessionRepo) {
        this.chatSessionRepo = chatSessionRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatSession> findAllByUser(UserEntity user){
        return chatSessionRepo.findAllByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatSession> findByUserUsernameAndId(String username, Long id){
        if(username == null || id == null || id < 0){
            throw new IllegalArgumentException("Invalid credentials: " + username + " " + id);
        }
        return chatSessionRepo.findByUserUsernameAndId(username, id);
    };

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatSession> findByIdAndUser(Long id, UserEntity user){
        if(user == null || id == null || id < 0){
            throw new IllegalArgumentException("Invalid credentials: " + user + " " + id);
        }
        return chatSessionRepo.findByIdAndUser(id, user);
    };

    @Override
    @Transactional
    public ChatSession save(ChatSession chatSession){
        if(chatSession == null){
            throw new IllegalArgumentException("Invalid credentials");
        }
        return chatSessionRepo.save(chatSession);
    }

    @Override
    @Transactional
    public void delete(ChatSession chatSession){
        if(chatSession == null){
            throw new IllegalArgumentException("Invalid credentials");
        }
        chatSessionRepo.delete(chatSession);
    }


}
