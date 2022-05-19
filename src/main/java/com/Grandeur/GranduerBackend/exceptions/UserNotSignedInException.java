package com.Grandeur.GranduerBackend.exceptions;

public class UserNotSignedInException extends RuntimeException {
    public UserNotSignedInException(String message) {
        super(message);
    }
}
