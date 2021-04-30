package com.oev.apis.grosspension.error;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.common.AdviceTrait;

@ControllerAdvice
@RequiredArgsConstructor
public class GrossPensionExceptionHandler implements AdviceTrait {

    private final Logger logger;

    @ExceptionHandler
    public ResponseEntity<Problem> handleNoEmfDataException(InvalidAgeException exception) {
        logger.warn("Request failed due InvalidAgeException: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Problem.builder()
                        .withTitle("The data is incorrect!")
                        .withDetail(exception.getMessage())
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleNoEmfDataException(NullPointerException exception) {
        logger.warn("Request failed due NullPointerException: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Problem.builder()
                        .withTitle("Required fields are not set!")
                        .withDetail(exception.getMessage())
                        .build());
    }
}
