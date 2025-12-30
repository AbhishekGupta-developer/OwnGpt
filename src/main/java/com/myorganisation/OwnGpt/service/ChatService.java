package com.myorganisation.OwnGpt.service;


import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    public String test() {
        ChatResponse response = chatModel.call(
                new Prompt(
                        "Calculate 17 × 23",
                        OllamaChatOptions.builder()
                                .model("deepseek-r1:1.5b")
                                .temperature(0D)
                                .enableThinking()
                                .build()
                ));

        String thinking = response.getResult().getMetadata().get("thinking");
        System.out.println("Reasoning: " + thinking);

        String answer = response.getResult().getOutput().getText();
        System.out.println("Answer: " + answer);

        return "Working";
    }
}
