package com.myorganisation.OwnGpt.service;


import com.myorganisation.OwnGpt.dto.request.ChatRequestDto;
import com.myorganisation.OwnGpt.dto.response.ChatResponseDto;
import com.myorganisation.OwnGpt.dto.response.LlmListResponseDto;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatModel chatModel;
    private final String model;

    public ChatServiceImpl(ChatModel chatModel, @Value ("${spring.ai.ollama.chat.options.model}") String model) {
        this.chatModel = chatModel;
        this.model = model;
    }

    @Override
    public LlmListResponseDto getLlmList() {
        String[] llmList = {
                "deepseek-r1:7b",
                "qwen3:1.7b",
                "qwen3:0.6b",
                "gemma3:1b",
                "gpt-oss:20b",
                "gemma3:270m",
                "deepseek-r1:1.5b"
        };
        return new LlmListResponseDto(llmList);
    }

    @Override
    public ChatResponseDto chatPrompt(ChatRequestDto chatRequestDto) {
        String prompt = chatRequestDto.getPrompt();
        String model = chatRequestDto.getModel();
        Double temperature = chatRequestDto.getTemperature();
        Boolean isEnableThinking = chatRequestDto.getIsEnableThinking();

        System.out.println("Temperature: " + temperature);
        System.out.println("Thinking: " + isEnableThinking);

        if (prompt == null || prompt.isBlank()) {
            return null;
        }

        if (model == null || model.isBlank()) {
            model = this.model;
        }

        if (temperature == null) {
            temperature = 0.0D;
        }

        if (isEnableThinking == null) {
            isEnableThinking = false;
        }

        ChatResponseDto chatResponseDto = new ChatResponseDto();

        ChatResponse response =
                isEnableThinking ?
                        chatModel.call(
                                new Prompt(
                                        prompt,
                                        OllamaChatOptions.builder()
                                                .model(model)
                                                .temperature(temperature)
                                                .enableThinking()
                                                .build()
                                ))
                        :
                        chatModel.call(
                                new Prompt(
                                        prompt,
                                        OllamaChatOptions.builder()
                                                .model(model)
                                                .temperature(temperature)
                                                .disableThinking()
                                                .build()
                                ));
        if(isEnableThinking) {
            System.out.println("Inside true");
            chatResponseDto.setThinking(response.getResult().getMetadata().get("thinking"));
            chatResponseDto.setAnswer(response.getResult().getOutput().getText());
        } else {
            System.out.println("Inside false");
            chatResponseDto.setAnswer(response.getResult().getOutput().getText());
        }

        return chatResponseDto;
    }
}
