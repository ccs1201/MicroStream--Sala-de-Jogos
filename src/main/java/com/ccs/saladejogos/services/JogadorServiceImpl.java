package com.ccs.saladejogos.services;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JogadorServiceImpl {

    private final MicroStreamRepository<Jogador> repository;

    public long store(Jogador jogador) {
        return repository.save(jogador);
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
        return repository.update(entity, id);
    }

    public void delete(UUID id) {
        repository.deleteDeleteById(id);
    }

    public void sairDaSala(UUID id) {
        var jogador = findById(id);

        if (ObjectUtils.isNotEmpty(jogador.getSala())) {
            jogador.sairDaSala();
            repository.update(jogador);
        }
    }
}