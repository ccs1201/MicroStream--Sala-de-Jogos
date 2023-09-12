package com.ccs.saladejogos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RepositoryException extends ResponseStatusException {
    public RepositoryException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
