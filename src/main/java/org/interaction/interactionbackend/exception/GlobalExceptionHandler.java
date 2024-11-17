package org.interaction.interactionbackend.exception;

import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = WorldViewException.class)
    public ResponseVO handleAIExternalException(WorldViewException e) {
        e.printStackTrace();
        return ResponseBuilder.buildErrorResponse(e.getMessage(), null);
    }
}

