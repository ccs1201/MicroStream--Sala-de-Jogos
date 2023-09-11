package com.ccs.saladejogos.repositories.rootinstances;

import com.ccs.saladejogos.model.entities.Jogador;
import lombok.Getter;
import one.microstream.integrations.spring.boot.types.Storage;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Storage
public class JogadorRoot {
    private final String name = "Jogador";
    private final Collection<Jogador> jogadores;

    public JogadorRoot() {
        this.jogadores = new ArrayList<>();
    }
}
