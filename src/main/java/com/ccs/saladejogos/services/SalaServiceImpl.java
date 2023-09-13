package com.ccs.saladejogos.services;

import com.ccs.saladejogos.exceptions.SalaServiceException;
import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl {

    private final SalaRepository repository;

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
        repository.update(sala);
    }

    public void encerrarSala(UUID salaId) {
        var sala = repository.findByID(salaId);
        sala.encerrarSala();
        repository.update(sala);
    }

    public Sala update(UUID salaId, Sala entity) {
        var sala = repository.findByID(salaId);

        if (sala.isEncerrada() || sala.isAberta()) {
            throw new SalaServiceException(HttpStatus.BAD_REQUEST,
                    "Não é permitido editar uma sala " + (sala.isAberta() ? "aberta" : "encerrada"));
        }
        return repository.update(entity, salaId);
    }

    public Sala fidById(UUID salaId) {
        return repository.findByID(salaId);
    }
}
