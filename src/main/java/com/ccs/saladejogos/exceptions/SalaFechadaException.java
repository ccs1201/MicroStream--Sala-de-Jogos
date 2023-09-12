package com.ccs.saladejogos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SalaFechadaException extends ResponseStatusException {
    public SalaFechadaException(HttpStatus status, String message) {
        super(status, message);
    }
}
