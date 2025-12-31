package com.myorganisation.OwnGpt.dto.response;

import lombok.Data;

@Data
public class GenericResponseDto {
    private Boolean isSuccess;
    private String message;
    private Object details;
}
