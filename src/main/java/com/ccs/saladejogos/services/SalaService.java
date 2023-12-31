package com.ccs.saladejogos.services;

import com.ccs.saladejogos.model.entities.Sala;

import java.util.Collection;
import java.util.UUID;

public interface SalaService {
    Sala save(Sala sala);

    Collection<Sala> findAll();

    Sala adicionarJogador(UUID salaId, UUID jogadorId);

    void abrirSala(UUID salaId);

    void encerrarSala(UUID salaId);

    Sala update(UUID salaId, Sala entity);

    void deleteById(UUID salaId);

    Collection<Sala> findByArgs(String[] args);

    Sala fidById(UUID salaId);
}
