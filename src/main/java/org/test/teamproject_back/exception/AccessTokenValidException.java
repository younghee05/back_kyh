package org.test.teamproject_back.exception;

public class AccessTokenValidException extends RuntimeException {
    public AccessTokenValidException(String message) {
        super(message);
    }
}
