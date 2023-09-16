package com.ccs.saladejogos.exceptions;

import com.ccs.saladejogos.model.entities.Jogador;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JogadorJaTemSalaException extends ResponseStatusException {
    public JogadorJaTemSalaException(HttpStatus httpStatus, Jogador jogador) {
        super(httpStatus,
                "Jogador " + jogador.getApelido() + " já esta na sala " + jogador.getSala().getDescricao());
    }
}
