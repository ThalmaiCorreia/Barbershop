package com.barbershop.exceptions;

public class AppointmentTimeUnavailableException extends RuntimeException {

    public AppointmentTimeUnavailableException() {
        super();
    }

    public AppointmentTimeUnavailableException(String message) {
        super(message);
    }

    public AppointmentTimeUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}

