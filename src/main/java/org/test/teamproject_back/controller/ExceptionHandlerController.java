package org.test.teamproject_back.controller;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.teamproject_back.exception.SignupException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<?> signupException(SignupException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
