package com.hackathon.police_community_app.backend.advice;

import com.hackathon.police_community_app.backend.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {
    private static final String DEFAULT_VALIDATION_MESSAGE = "Validation failed";
    private static final String DEFAULT_MESSAGE = "Invalid Request";


    @ExceptionHandler(AuthorizationDeniedException.class)
    public ProblemDetail accessDeniedExceptionHandler(AuthorizationDeniedException e) {
        log.error(e.getMessage(), e);

        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public ProblemDetail exceptionHandler(BaseException e) {
        log.error(e.getMessage(), e);

        return ProblemDetail.forStatusAndDetail(e.getHttpStatus(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validationExceptionHandler(MethodArgumentNotValidException e) {
        log.error(DEFAULT_VALIDATION_MESSAGE, e);

        Optional<String> firstErrorMessage = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(msg -> msg != null && !msg.isEmpty())
                .findFirst();

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(firstErrorMessage.orElse(DEFAULT_VALIDATION_MESSAGE));
        problemDetail.setTitle(DEFAULT_MESSAGE);

        return problemDetail;
    }
}

