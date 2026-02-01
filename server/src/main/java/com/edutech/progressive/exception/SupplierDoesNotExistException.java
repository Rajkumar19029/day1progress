package com.edutech.progressive.exception;

public class SupplierDoesNotExistException extends RuntimeException {

    public SupplierDoesNotExistException(String string) {
        super(string);
    }
}