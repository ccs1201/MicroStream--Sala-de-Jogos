package com.ccs.saladejogos.repositories;

import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.model.entities.SalaJogosEntity;

import java.util.UUID;

public interface SalaRepository<T extends SalaJogosEntity> extends MicroStreamRepository<Sala> {
    Sala adcionarJogador(UUID salaId, UUID jogadorId);
}
