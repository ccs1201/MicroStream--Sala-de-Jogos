package com.ccs.saladejogos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SalaLotadaException extends ResponseStatusException {
    public SalaLotadaException(HttpStatus status, String message) {
        super(status, message);
    }
}
