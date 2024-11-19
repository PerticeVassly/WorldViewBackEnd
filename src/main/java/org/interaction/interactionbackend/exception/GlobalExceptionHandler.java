package org.interaction.interactionbackend.exception;

import org.slf4j.Logger;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = WorldViewException.class)
    public ResponseVO handleAIExternalException(WorldViewException e) {
//        e.printStackTrace();
        logger.debug("AI External Exception: {}", e.getMessage());
        return ResponseBuilder.buildErrorResponse(e.getMessage(), null);
    }
}

