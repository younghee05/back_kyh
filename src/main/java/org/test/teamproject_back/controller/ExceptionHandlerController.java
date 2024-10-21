package org.test.teamproject_back.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.teamproject_back.exception.AccessTokenValidException;
import org.test.teamproject_back.exception.SignupException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.test.teamproject_back.exception.UnauthorizedAccessException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<?> signupException(SignupException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(AccessTokenValidException.class)
    public ResponseEntity<?> accessTokenValidException(AccessTokenValidException e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }

}
