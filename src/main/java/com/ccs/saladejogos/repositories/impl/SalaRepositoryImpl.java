package com.ccs.saladejogos.repositories.impl;

import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.SalaRepository;
import lombok.extern.slf4j.Slf4j;
import one.microstream.storage.types.StorageManager;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;

@Repository
@Slf4j
public class SalaRepositoryImpl extends MicroStreamRepositoryImpl<Sala> implements SalaRepository {

    public SalaRepositoryImpl(StorageManager storageManager) {
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

    @Override
    public void updateJogadores(Sala sala) {
        log.info("### Atualizando Jogadores da Sala");
        storageManager.store(sala.getJogadores());
    }
}
