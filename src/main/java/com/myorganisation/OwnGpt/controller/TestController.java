package com.myorganisation.OwnGpt.controller;

import com.myorganisation.OwnGpt.service.ChatService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final ChatService chatService;

    public TestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<String> test() {
        return new ResponseEntity<>(chatService.test(), HttpStatusCode.valueOf(200));
    }

}
