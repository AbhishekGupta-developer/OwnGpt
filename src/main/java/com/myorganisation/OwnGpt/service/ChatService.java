package com.myorganisation.OwnGpt.service;

import com.myorganisation.OwnGpt.dto.request.ChatRequestDto;
import com.myorganisation.OwnGpt.dto.response.ChatResponseDto;
import com.myorganisation.OwnGpt.dto.response.LlmListResponseDto;

public interface ChatService {
    LlmListResponseDto getLlmList();
    ChatResponseDto chatPrompt(ChatRequestDto chatRequestDto);
}
