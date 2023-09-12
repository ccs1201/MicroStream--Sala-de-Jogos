package com.ccs.saladejogos.repositories.rootinstances;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.model.entities.Sala;
import lombok.Getter;
import one.microstream.integrations.spring.boot.types.Storage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Collection;
import java.util.LinkedHashSet;

@Getter
@Storage
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataRoot {
    private final String name = "Jogador";
    private final Collection<Jogador> jogadores;
    private final Collection<Sala> salas;

    public DataRoot() {
        this.jogadores = new LinkedHashSet<>();
        this.salas = new LinkedHashSet<>();
    }
}
