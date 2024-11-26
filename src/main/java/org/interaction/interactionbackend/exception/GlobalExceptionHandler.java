package org.interaction.interactionbackend.exception;

import org.slf4j.Logger;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = WorldViewException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO handleAIExternalException(WorldViewException e) {
        logger.info("An Exception: {}", e.getMessage());
//        System.out.println(e.getStackTrace());
        return ResponseBuilder.buildErrorResponse(e.getMessage(), null);
    }
}

