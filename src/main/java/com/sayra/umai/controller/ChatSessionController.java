package com.sayra.umai.controller;

import com.sayra.umai.model.dto.ChatSessionDTO;
import com.sayra.umai.service.impl.ChatSessionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/chat-sessions")
public class ChatSessionController {
    private final ChatSessionServiceImpl chatSessionService;
    public ChatSessionController(ChatSessionServiceImpl chatSessionService) {
        this.chatSessionService = chatSessionService;
    }

    @GetMapping()
    public ResponseEntity<List<ChatSessionDTO>> getAllChatSessions(){
        return ResponseEntity.ok(chatSessionService.getUserSessions());
    }

//    public ResponseEntity<List<ChatSessionDTO>> getChatSession(Principal principal, Long id){
//        return ResponseEntity.ok(chatSessionService.createOrContinueChatSession(principal, id));
//    }
}
