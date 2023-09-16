package com.ccs.saladejogos.services.impl;

import com.ccs.saladejogos.exceptions.SalaServiceException;
import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.SalaRepository;
import com.ccs.saladejogos.services.JogadorService;
import com.ccs.saladejogos.services.SalaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

        if (CollectionUtils.isEmpty(sala.getJogadores())) {
            log.info("");
            log.info("### Primeiro jogador da Sala deve ser o NODO Root por isso persistimos a sala inteira");
            log.info("");
            adicionarPrimeiroJogador(sala, jogador);
        } else {
            log.info("");
            log.info("### Não é NODO Root, então devemos persistir a coleção dos jogadores da sala.");
            log.info("");
            adicionarNovoJogador(sala, jogador);
        }
        return sala;
    }

    private void adicionarPrimeiroJogador(Sala sala, Jogador jogador) {
        sala.addJogador(jogador);
        jogadorService.update(jogador);
        repository.update(sala);
    }

    private void adicionarNovoJogador(Sala sala, Jogador jogador) {
        sala.addJogador(jogador);
        jogadorService.update(jogador);
        repository.updateJogadores(sala);
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
        sala.getJogadores().removeIf(jogador -> sala.removerJogador(jogador));
        repository.delete(sala);
    }
}
