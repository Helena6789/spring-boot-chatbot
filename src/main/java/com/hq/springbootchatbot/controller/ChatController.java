package com.hq.springbootchatbot.controller;

import com.hq.springbootchatbot.model.ChatMessage;
import com.hq.springbootchatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {
    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/chat")
    public String getChatMessage(@RequestBody ChatMessage chatMessage) {
        return chatService.chat(chatMessage);
    }
}
