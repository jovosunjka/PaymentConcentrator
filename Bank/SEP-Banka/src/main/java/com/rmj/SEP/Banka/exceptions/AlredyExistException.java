package com.rmj.SEP.Banka.exceptions;

public class AlredyExistException extends RuntimeException {
    public AlredyExistException(){}

    public AlredyExistException(String message)
    {
        super(message);
    }
}
