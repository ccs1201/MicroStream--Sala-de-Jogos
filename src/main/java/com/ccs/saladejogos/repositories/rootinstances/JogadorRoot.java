package com.ccs.saladejogos.repositories.rootinstances;

import com.ccs.saladejogos.model.entities.Jogador;
import lombok.Getter;
import one.microstream.integrations.spring.boot.types.Storage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Storage
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JogadorRoot {
    private final String name = "Jogador";
    private final Collection<Jogador> jogadores;

    public JogadorRoot() {
        this.jogadores = new ArrayList<>();
    }
}
