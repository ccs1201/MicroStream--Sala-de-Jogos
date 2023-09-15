package com.ccs.saladejogos.services.impl;

import com.ccs.saladejogos.exceptions.SalaServiceException;
import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.JogadorRepository;
import com.ccs.saladejogos.repositories.SalaRepository;
import com.ccs.saladejogos.services.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl implements SalaService {

    private final SalaRepository repository;
    private final JogadorRepository jogadorRepository;

    @Override
    public long save(Sala sala) {
        return repository.save(sala);
    }

    @Override
    public Collection<Sala> findAll() {
        return repository.findAll();
    }

    @Override
    public Sala adicionarJogador(UUID salaId, UUID jogadorId) {
        var jogador = jogadorRepository.findByID(jogadorId);
        var sala = repository.findByID(salaId);
        sala.addJogador(jogador);

        jogadorRepository.update(jogador);

        return repository.update(sala);
    }

    @Override
    public void abrirSala(UUID salaId) {
        var sala = repository.findByID(salaId);
        sala.abrirSala();
        repository.update(sala);
    }

    @Override
    public void encerrarSala(UUID salaId) {
        var sala = repository.findByID(salaId);
        sala.encerrarSala();
        repository.update(sala);
    }

    @Override
    public Sala update(UUID salaId, Sala entity) {
        var sala = repository.findByID(salaId);

        if (sala.isEncerrada() || sala.isAberta()) {
            throw new SalaServiceException(HttpStatus.BAD_REQUEST,
                    "Não é permitido editar uma sala " + (sala.isAberta() ? "aberta" : "encerrada"));
        }
        return repository.update(entity, salaId);
    }

    @Override
    public Sala fidById(UUID salaId) {
        return repository.findByID(salaId);
    }
}
