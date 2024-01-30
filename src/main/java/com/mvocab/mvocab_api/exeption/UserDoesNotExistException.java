package com.mvocab.mvocab_api.exeption;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("does not exists");
    }
}
