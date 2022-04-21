package com.wp.project.beautysalon.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{

    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password!");
    }
}
