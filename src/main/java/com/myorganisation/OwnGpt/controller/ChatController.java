package com.myorganisation.OwnGpt.controller;

import com.myorganisation.OwnGpt.dto.request.ChatRequestDto;
import com.myorganisation.OwnGpt.dto.response.ChatResponseDto;
import com.myorganisation.OwnGpt.dto.response.LlmListResponseDto;
import com.myorganisation.OwnGpt.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/llm")
    public ResponseEntity<LlmListResponseDto> getLlmList() {
        return new ResponseEntity<>(chatService.getLlmList(), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<ChatResponseDto> chatAi(@RequestBody ChatRequestDto chatRequestDto) {
        return new ResponseEntity<>(chatService.chatPrompt(chatRequestDto), HttpStatusCode.valueOf(200));
    }

}
