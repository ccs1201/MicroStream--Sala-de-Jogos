package com.ccs.saladejogos.services.impl;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.repositories.JogadorRepository;
import com.ccs.saladejogos.repositories.SalaRepository;
import com.ccs.saladejogos.services.JogadorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JogadorServiceImpl implements JogadorService {

    private final JogadorRepository repository;
    private final SalaRepository salaRepository;

    @Override
    public Jogador save(Jogador jogador) {
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
        var jogador = repository.findByID(id);

        BeanUtils.copyProperties(entity, jogador, "id", "sala");

        return repository.update(jogador, id);
    }

    @Override
    public void delete(UUID id) {
        var jogador = findById(id);
        var sala = jogador.getSala();
        jogador.sairDaSala();
        salaRepository.update(sala);
        repository.delete(jogador);
    }

    @Override
    public void sairDaSala(UUID id) {
        var jogador = findById(id);

        if (ObjectUtils.isNotEmpty(jogador.getSala())) {
            jogador.sairDaSala();
            repository.update(jogador);
        }
    }

    @Override
    public Jogador update(Jogador jogador) {
        return repository.update(jogador);
    }

}
