package com.ccs.saladejogos.services;

import com.ccs.saladejogos.model.entities.Jogador;

import java.util.Collection;
import java.util.UUID;

public interface JogadorService {
    Jogador save(Jogador jogador);

    Collection<Jogador> findAll();

    Jogador findById(UUID id);

    Collection<Jogador> findByArgs(String... args);

    Jogador update(Jogador entity, UUID id);

    void delete(UUID id);

    void sairDaSala(UUID id);

    Jogador update(Jogador jogador);

}
