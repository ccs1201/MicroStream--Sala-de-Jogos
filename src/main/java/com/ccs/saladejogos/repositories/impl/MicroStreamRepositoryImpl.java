package com.ccs.saladejogos.repositories.impl;

import com.ccs.saladejogos.exceptions.RepositoryException;
import com.ccs.saladejogos.model.entities.SalaJogosEntity;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import com.ccs.saladejogos.repositories.rootinstances.DataRoot;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import one.microstream.storage.types.StorageManager;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class MicroStreamRepositoryImpl<T extends SalaJogosEntity> implements MicroStreamRepository<T> {

    protected final StorageManager storageManager;
    @Autowired
    private DataRoot root;

    @PostConstruct
    private void init() {
        storageManager.setRoot(root);
    }

    protected DataRoot getRoot() {
        return (DataRoot) storageManager.root();
    }

    @Override
    public long save(T entity) {
        var collection = getColletionRoot();
        if (BooleanUtils.isFalse(collection.add(entity))) {
            throw new RepositoryException(HttpStatus.BAD_REQUEST,
                    entity.getClass().getSimpleName() + " já Cadastrado.");
        }
        return storageManager.store(collection);
    }

    @Override
    public Collection<T> findAll() {
        return getColletionRoot();
    }

    @Override
    public T findByID(UUID id) {
        return getColletionRoot()
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RepositoryException(HttpStatus.NOT_FOUND,
                                "Objeto não encontrado."));
    }

    @Override
    public void deleteDeleteById(UUID id) {
        var toRemove = findByID(id);
        if (BooleanUtils.isFalse(getColletionRoot().remove(toRemove))) {
            throw new RepositoryException(HttpStatus.BAD_REQUEST,
                    "Não foi Possível remover " + toRemove.getClass().getSimpleName());
        }
    }

    @Override
    public void delete(T entity) {
        if (BooleanUtils.isFalse(getColletionRoot().remove(entity))) {
            throw new RepositoryException(HttpStatus.NOT_FOUND, " Objeto não encontrado.");
        }
    }

    @Override
    public void deleteAll() {
        getColletionRoot().clear();
    }

    @Override
    public abstract Collection<T> findByArgs(String... args);

    @Override
    public T update(T entity, UUID id) {
        var toUpdate = findByID(id);
        BeanUtils.copyProperties(entity, toUpdate);
        storageManager.store(toUpdate);
        return toUpdate;
    }

    protected abstract Collection<T> getColletionRoot();

    public T update(T entity) {
        storageManager.store(entity);
        return entity;
    }
}
