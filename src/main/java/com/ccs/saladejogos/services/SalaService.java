package com.ccs.saladejogos.services;

import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository<Sala> repository;

    public long save(Sala sala) {
        return repository.save(sala);
    }

    public Collection<Sala> findAll() {
        return repository.findAll();
    }

    public Sala adicionarJogador(UUID salaId, UUID jogadorId) {
        return repository.adcionarJogador(salaId, jogadorId);
    }

    public void abrirSala(UUID salaId) {
        var sala = repository.findByID(salaId);
        sala.abrirSala();
        repository.update(sala, salaId);
    }

    public void encerrarSala(UUID salaId) {
        var sala = repository.findByID(salaId);
        sala.encerrarSala();
        repository.update(sala, salaId);
    }
}
