package com.wp.project.beautysalon.model.exceptions;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException() {
        super("Passwords not match!");
    }
}
