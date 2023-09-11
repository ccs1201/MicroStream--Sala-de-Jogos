package com.ccs.saladejogos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JogadorException extends ResponseStatusException {
    public JogadorException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public JogadorException(String message, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, message, cause);
    }
}
