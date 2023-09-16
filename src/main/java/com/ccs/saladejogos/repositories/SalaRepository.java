package com.ccs.saladejogos.repositories;

import com.ccs.saladejogos.model.entities.Sala;

public interface SalaRepository extends MicroStreamRepository<Sala> {
    void updateJogadores(Sala sala);
}
