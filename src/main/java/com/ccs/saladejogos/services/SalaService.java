package com.ccs.saladejogos.services;

import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final MicroStreamRepository<Sala> repository;

    public long save(Sala sala) {
        return repository.store(sala);
    }

    public Collection<Sala> findAll() {
        return repository.findAll();
    }
}
