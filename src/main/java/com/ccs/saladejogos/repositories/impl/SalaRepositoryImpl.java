package com.ccs.saladejogos.repositories.impl;

import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.SalaRepository;
import com.ccs.saladejogos.repositories.rootinstances.DataRoot;
import lombok.extern.slf4j.Slf4j;
import one.microstream.storage.types.StorageManager;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;

@Repository
@Slf4j
public class SalaRepositoryImpl extends MicroStreamRepositoryImpl<Sala> implements SalaRepository {


    public SalaRepositoryImpl(StorageManager storageManager, DataRoot root) {
        super(storageManager, root);
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
    public Sala update(Sala sala) {
        log.info("### Atualizando Jogadores da Sala");
        super.update(sala);
        storageManager.store(sala.getJogadores());
        return sala;
    }
}
