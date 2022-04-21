package com.wp.project.beautysalon.model.exceptions;

public class ServiceIdReservedException extends RuntimeException{

    public ServiceIdReservedException(String message){
        super(message);
    }
    public ServiceIdReservedException(){}
}
