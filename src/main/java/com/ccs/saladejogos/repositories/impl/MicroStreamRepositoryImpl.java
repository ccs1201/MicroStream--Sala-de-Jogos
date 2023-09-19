package com.ccs.saladejogos.repositories.impl;

import com.ccs.saladejogos.exceptions.RepositoryException;
import com.ccs.saladejogos.model.entities.SalaJogosEntity;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import com.ccs.saladejogos.repositories.rootinstances.DataRoot;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.microstream.persistence.binary.jdk17.types.BinaryHandlersJDK17;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageFoundation;
import one.microstream.storage.types.StorageManager;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public abstract class MicroStreamRepositoryImpl<T extends SalaJogosEntity> implements MicroStreamRepository<T> {

    protected final StorageManager storageManager;
    private final EmbeddedStorageFoundation<?> foundation = EmbeddedStorage.Foundation();

    private final DataRoot root;

    protected DataRoot getRoot() {
        return (DataRoot) storageManager.root();
    }

    @PostConstruct
    private void init() {
        storageManager.setRoot(root);
        foundation.onConnectionFoundation(BinaryHandlersJDK17::registerJDK17TypeHandlers);
    }

    protected void store() {
        storageManager.store(getColletionRoot());
    }

    @Override
    public T save(T entity) {
        log.info("### Armazenando {} ###", getClassName());

        if (BooleanUtils.isFalse(getColletionRoot().add(entity))) {
            throw new RepositoryException(HttpStatus.BAD_REQUEST,
                    entity.getClass().getSimpleName() + " já Cadastrado.");
        }

        store();
        return entity;
    }

    @Override
    public Collection<T> findAll() {
        log.info("### Buscando todos os registros {} ###", getClassName());
        return getColletionRoot();
    }

    @Override
    public T findByID(UUID id) {
        log.info("### Buscando {} pelo ID: {} ###", getClassName(), id);
        return getColletionRoot()
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RepositoryException(HttpStatus.NOT_FOUND,
                                getClassName() + " não encontrado."));
    }

    @Override
    public void deleteById(UUID id) {
        var toRemove = findByID(id);

        log.info("### Removendo {} ID: {} ###", getClassName(), id);

        if (BooleanUtils.isFalse(getColletionRoot().remove(toRemove))) {
            throw new RepositoryException(HttpStatus.BAD_REQUEST,
                    "Não foi Possível remover " + toRemove.getClass().getSimpleName());
        }
        store();
    }

    @Override
    public void delete(T entity) {
        log.info("### Removendo {} ID: {} ###", getClassName(), entity.getId());
        if (BooleanUtils.isFalse(getColletionRoot().remove(entity))) {
            throw new RepositoryException(HttpStatus.NOT_FOUND, entity.getClass().getSimpleName() + "  não encontrado.");
        }
        store();
    }

    @Override
    public void deleteAll() {
        log.info("### Removendo todos os registros: {} ###", getClassName());
        getColletionRoot().clear();
        store();
    }

    @Override
    public T update(T entity, UUID id) {
        log.info("### Atualizando {} ID: {} ###", entity.getClass().getSimpleName(), id);
        var toUpdate = findByID(id);
        BeanUtils.copyProperties(entity, toUpdate);
        store();
        return toUpdate;
    }

    protected abstract Collection<T> getColletionRoot();

    public T update(T entity) {
        log.info("### Atualizando {} ###", entity.getClass().getSimpleName());
        storageManager.store(entity);
        return entity;
    }

    private String getClassName() {
        var obj = getColletionRoot()
                .stream()
                .findFirst();

        return obj.map(t -> t.getClass().getSimpleName()).orElse("");
    }
}

