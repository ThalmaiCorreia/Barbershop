package com.barbershop.exceptions;

public class InvalidAppointmentUpdateException extends RuntimeException {

    public InvalidAppointmentUpdateException() {
        super();
    }

    public InvalidAppointmentUpdateException(String message) {
        super(message);
    }

    public InvalidAppointmentUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAppointmentUpdateException(Throwable cause) {
        super(cause);
    }
}
