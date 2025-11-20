package com.sayra.umai.repo_service;

import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.model.entity.work.ChatSession;

import java.util.List;
import java.util.Optional;

public interface ChatSessionDataService {
    List<ChatSession> findAllByUser(UserEntity user);

    Optional<ChatSession> findByUserUsernameAndId(String username, Long id);

    Optional<ChatSession> findByIdAndUser(Long id, UserEntity user);

    ChatSession save(ChatSession chatSession);

    void delete(ChatSession chatSession);
}
