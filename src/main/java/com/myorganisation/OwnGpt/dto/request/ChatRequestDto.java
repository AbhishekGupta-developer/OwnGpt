package com.myorganisation.OwnGpt.dto.request;

import lombok.Data;

@Data
public class ChatRequestDto {
    private String prompt;
    private String model;
    private Double temperature;
    private Boolean isEnableThinking;
}
