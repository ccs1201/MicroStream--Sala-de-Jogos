package com.ccs.saladejogos.repositories;

import com.ccs.saladejogos.model.entities.Sala;

import java.util.UUID;

public interface SalaRepository extends MicroStreamRepository<Sala> {
    Sala adcionarJogador(UUID salaId, UUID jogadorId);
}
