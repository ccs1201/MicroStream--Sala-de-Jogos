package com.ccs.saladejogos.services;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final MicroStreamRepository<Jogador> repository;

    public long store(Jogador jogador) {
        return repository.store(jogador);
    }

    public Collection<Jogador> findAll() {
        return repository.findAll();
    }

    public Jogador findById(UUID id) {
        return repository.findByID(id);
    }

    public Collection<Jogador> findByArgs(String... args) {
        return repository.findByArgs(args);
    }

    public Jogador update(Jogador entity, UUID id) {
        entity.setId(id);
        return repository.update(entity);
    }

    public void delete(UUID id) {
        repository.deleteDeleteById(id);
    }
}
