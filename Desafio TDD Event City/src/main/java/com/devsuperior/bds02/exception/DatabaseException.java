package com.devsuperior.bds02.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {
        super(msg);
    }
}

