package com.barbershop.exceptions;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException() {
        super();
    }

    public AppointmentNotFoundException(String message) {
        super(message);
    }

    public AppointmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

