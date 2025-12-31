package com.myorganisation.OwnGpt.exception;

import com.myorganisation.OwnGpt.dto.response.GenericResponseDto;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NonTransientAiException.class)
    public ResponseEntity<GenericResponseDto> handleNonTransientAiException(NonTransientAiException e) {
        String message = e.getMessage()
                .substring(e.getMessage().indexOf(':') + 1)
                .replace("\"", "")
                .replace("\\", "")
                .replace("}", "");

        GenericResponseDto genericResponseDto = new GenericResponseDto();
        genericResponseDto.setIsSuccess(false);
        genericResponseDto.setMessage("An exception occurred: " + message);
        genericResponseDto.setDetails(Map.of("Ollama server", e.getMessage()));

        return new ResponseEntity<>(genericResponseDto, HttpStatusCode.valueOf(400));
    }

}
