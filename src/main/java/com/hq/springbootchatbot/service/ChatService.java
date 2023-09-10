package com.hq.springbootchatbot.service;

import com.hq.springbootchatbot.model.ChatMessage;
import com.hq.springbootchatbot.util.ChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private ChatUtil chatUtil;

    @Autowired
    public ChatService(ChatUtil chatUtil) {
        this.chatUtil = chatUtil;
    }

    public String chat(ChatMessage chatMessage) {
        return chatUtil.ask(chatMessage);
    }
}
