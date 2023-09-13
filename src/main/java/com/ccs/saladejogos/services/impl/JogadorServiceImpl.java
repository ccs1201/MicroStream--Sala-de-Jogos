package com.ccs.saladejogos.services.impl;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import com.ccs.saladejogos.services.JogadorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JogadorServiceImpl implements JogadorService {

    private final MicroStreamRepository<Jogador> repository;

    @Override
    public long store(Jogador jogador) {
        return repository.save(jogador);
    }

    @Override
    public Collection<Jogador> findAll() {
        return repository.findAll();
    }

    @Override
    public Jogador findById(UUID id) {
        return repository.findByID(id);
    }

    @Override
    public Collection<Jogador> findByArgs(String... args) {
        return repository.findByArgs(args);
    }

    @Override
    public Jogador update(Jogador entity, UUID id) {
        return repository.update(entity, id);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteDeleteById(id);
    }

    @Override
    public void sairDaSala(UUID id) {
        var jogador = findById(id);

        if (ObjectUtils.isNotEmpty(jogador.getSala())) {
            jogador.sairDaSala();
            repository.update(jogador);
        }
    }
}
