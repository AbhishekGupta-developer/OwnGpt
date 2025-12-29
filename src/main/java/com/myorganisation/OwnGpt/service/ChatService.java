package com.myorganisation.OwnGpt.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final OllamaChatModel chatModel;

    public ChatService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String test() {
        ChatResponse response = chatModel.call(
                new Prompt(
                        "Generate the names of 5 famous Indian celebrity.",
                        OllamaChatOptions.builder()
//                                .model(OllamaModel.LLAMA3_1)
                                .temperature(0.4)
                                .build()
                ));

        return response.toString();
    }
}
