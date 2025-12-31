package com.myorganisation.OwnGpt.dto.response;

import lombok.Data;

@Data
public class LlmListResponseDto {
    private String[] llmsList;

    public LlmListResponseDto(String[] llmsList) {
        this.llmsList = llmsList;
    }
}
