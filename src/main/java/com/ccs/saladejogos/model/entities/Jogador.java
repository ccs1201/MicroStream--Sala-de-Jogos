package com.ccs.saladejogos.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(of = {"apelido"}, callSuper = false)
public class Jogador extends SalaJogosEntity {

    private String nomeCompleto;
    private String apelido;
    private Sala sala;

    public void sairDaSala() {
        sala.getJogadores().remove(this);
        sala = null;
    }
}

