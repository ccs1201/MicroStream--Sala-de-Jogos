package com.ccs.saladejogos.repositories.impl;

import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.JogadorRepository;
import com.ccs.saladejogos.repositories.SalaRepository;
import one.microstream.storage.types.StorageManager;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;

@Repository
public class SalaRepositoryImpl extends MicroStreamRepositoryImpl<Sala> implements SalaRepository {

    public SalaRepositoryImpl(StorageManager storageManager, JogadorRepository jogadorRepository) {
        super(storageManager);
    }

    @Override
    public Collection<Sala> findByArgs(String... args) {
        return getColletionRoot()
                .stream()
                .filter(sala ->
                        Arrays.stream(args)
                                .anyMatch(arg -> sala.getDescricao().contains(arg))
                ).toList();
    }

    @Override
    protected Collection<Sala> getColletionRoot() {
        return super.getRoot().getSalas();
    }
}
