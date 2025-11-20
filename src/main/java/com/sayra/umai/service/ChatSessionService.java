package com.sayra.umai.service;

import com.sayra.umai.model.dto.ChatSessionDTO;
import com.sayra.umai.model.entity.work.ChatSession;
import com.sayra.umai.model.request.ChatSessionRequest;
import jakarta.xml.bind.ValidationException;

import java.security.Principal;
import java.util.List;

public interface ChatSessionService {
    List<ChatSessionDTO> getUserSessions();
    ChatSessionDTO getChatSession(Long id);
    ChatSessionDTO createSession(ChatSessionRequest chatSessionDTO);

    void delete(Long id);
}
