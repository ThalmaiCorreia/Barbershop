package com.barbershop.exceptions;

public class BarberAlreadyExistsException extends RuntimeException {
    public BarberAlreadyExistsException(String message) {
        super(message);
    }
}

