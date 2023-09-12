package com.ccs.saladejogos.model.entities;

import com.ccs.saladejogos.exceptions.JogadorException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class Jogador extends SalaJogosEntity {

    private String nomeCompleto;
    private String apelido;
    private Sala sala;

    public void setSala(Sala sala) {
        if (this.sala != null) {
            throw new JogadorException("Jogador já esta na sala: " + this.sala.getDescricao() + " e não pode estar em mais de uma sala simultaneamente.");
        } else {
            this.sala = sala;
        }
    }

    public void sairDaSala() {
        sala.removerJogador(this);
    }

    public void removerSala() {
        sala = null;
    }
}

