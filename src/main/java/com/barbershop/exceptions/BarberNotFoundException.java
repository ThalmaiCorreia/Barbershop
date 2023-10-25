package com.barbershop.exceptions;

public class BarberNotFoundException extends RuntimeException {
    public BarberNotFoundException(String message) {
        super(message);
    }
}
