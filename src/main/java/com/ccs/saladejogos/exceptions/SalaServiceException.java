package com.ccs.saladejogos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SalaServiceException extends ResponseStatusException {
    public SalaServiceException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
