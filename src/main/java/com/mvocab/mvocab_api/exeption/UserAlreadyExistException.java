package com.mvocab.mvocab_api.exeption;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super("User already exist");
    }
}
