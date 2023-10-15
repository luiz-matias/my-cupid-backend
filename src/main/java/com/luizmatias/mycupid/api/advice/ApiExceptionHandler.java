package com.luizmatias.mycupid.api.advice;

import com.luizmatias.mycupid.api.dtos.responses.ApiErrorResponseDTO;
import com.luizmatias.mycupid.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponseDTO handleInvalidArguments(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });


        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid arguments",
                errorMap
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTokenException.class)
    public ApiErrorResponseDTO handleInvalidToken(InvalidTokenException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FailedToSendNotificationException.class)
    public ApiErrorResponseDTO handleFailedToSendEmail(FailedToSendNotificationException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponseDTO handleResourceNotFound(ResourceNotFoundException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(LoginFailedException.class)
    public ApiErrorResponseDTO handleLoginFailed(LoginFailedException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(PasswordMismatchException.class)
    public ApiErrorResponseDTO handlePasswordMismatch(PasswordMismatchException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(InactiveUserException.class)
    public ApiErrorResponseDTO handleInactiveUser(InactiveUserException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ApiErrorResponseDTO handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler({LikeOnSameUserException.class, LikeOnSameUserTypeException.class})
    public ApiErrorResponseDTO handleLikeExceptions(Exception ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotAuthorizedException.class)
    public ApiErrorResponseDTO handleNotAuthorized(NotAuthorizedException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage()
        );
    }

}
