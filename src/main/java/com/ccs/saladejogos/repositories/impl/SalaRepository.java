package com.ccs.saladejogos.repositories.impl;

import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import com.ccs.saladejogos.repositories.rootinstances.SalaRoot;
import lombok.RequiredArgsConstructor;
import one.microstream.storage.types.StorageManager;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SalaRepository implements MicroStreamRepository<Sala> {

    private final StorageManager storageManager;
    private final SalaRoot root;

    private SalaRoot getRoot() {
        storageManager.setRoot(root);
        return (SalaRoot) storageManager.root();
    }

    @Override
    public long store(Sala entity) {
        getRoot();
        return storageManager.store(entity);
    }

    @Override
    public Collection<Sala> findAll() {
        return getRoot().getSalas();
    }

    @Override
    public Sala findByID(UUID id) {
        return null;
    }

    @Override
    public void deleteDeleteById(UUID id) {

    }

    @Override
    public void delete(Sala entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<Sala> findByArgs(String... args) {
        return null;
    }

    @Override
    public Sala update(Sala entity) {
        return null;
    }

    @Override
    public boolean hasDataBase() {
        return false;
    }
}
