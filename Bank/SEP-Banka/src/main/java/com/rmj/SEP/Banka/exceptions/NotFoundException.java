package com.rmj.SEP.Banka.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){}

    public NotFoundException(String message)
    {
        super(message);
    }
}
