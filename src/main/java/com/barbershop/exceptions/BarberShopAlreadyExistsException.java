package com.barbershop.exceptions;

public class BarberShopAlreadyExistsException extends RuntimeException {
    public BarberShopAlreadyExistsException(String message) {
        super(message);
    }
}
