package com.ccs.saladejogos.services.impl;

import com.ccs.saladejogos.exceptions.SalaServiceException;
import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.SalaRepository;
import com.ccs.saladejogos.services.JogadorService;
import com.ccs.saladejogos.services.SalaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaServiceImpl implements SalaService {

    private final SalaRepository repository;
    private final JogadorService jogadorService;

    @Override
    public Sala save(Sala sala) {
        return repository.save(sala);
    }

    @Override
    public Collection<Sala> findAll() {
        return repository.findAll();
    }

    @Override
    public Sala adicionarJogador(UUID salaId, UUID jogadorId) {
        var jogador = jogadorService.findById(jogadorId);
        var sala = repository.findByID(salaId);

        log.info("### {} Adicionando jogador a sala", this.getClass().getSimpleName());
        sala.addJogador(jogador);
        repository.update(sala);
        jogadorService.update(jogador);
        return sala;
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

    @Override
    public void deleteById(UUID salaId) {
        var sala = repository.findByID(salaId);
        var jogadores = sala.getJogadores();

        if (sala.isEncerrada()) {
            throw new SalaServiceException(HttpStatus.BAD_REQUEST, "Não é possível remover uma sala encerrada.");
        }

        sala.encerrarSala();

        jogadores.forEach(jogadorService::update);

        repository.delete(sala);
    }

    @Override
    public Collection<Sala> findByArgs(String... args) {
        return repository.findByArgs(args);
    }
}
