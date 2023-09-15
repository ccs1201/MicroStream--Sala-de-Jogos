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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public abstract class MicroStreamRepositoryImpl<T extends SalaJogosEntity> implements MicroStreamRepository<T> {

    protected final StorageManager storageManager;
    private final EmbeddedStorageFoundation<?> foundation = EmbeddedStorage.Foundation();

    @Autowired
    private DataRoot root;

    @PostConstruct
    private void init() {
        storageManager.setRoot(root);
        foundation.onConnectionFoundation(BinaryHandlersJDK17::registerJDK17TypeHandlers);
    }

    protected DataRoot getRoot() {
        return (DataRoot) storageManager.root();
    }

    @Override
    public long save(T entity) {
        log.info("### Armazenando {} ###", entity.getClass().getSimpleName());
        var collection = getColletionRoot();
        if (BooleanUtils.isFalse(collection.add(entity))) {
            throw new RepositoryException(HttpStatus.BAD_REQUEST,
                    entity.getClass().getSimpleName() + " já Cadastrado.");
        }
        return storageManager.store(collection);
    }

    @Override
    public Collection<T> findAll() {
        log.info("### Buscando todos os registros ###");
        return getColletionRoot();
    }

    @Override
    public T findByID(UUID id) {
        log.info("### Buscando pelo ID: {} ###", id);
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
        log.info("### Removendo ID: {} ###", id);
        var toRemove = findByID(id);
        if (BooleanUtils.isFalse(getColletionRoot().remove(toRemove))) {
            throw new RepositoryException(HttpStatus.BAD_REQUEST,
                    "Não foi Possível remover " + toRemove.getClass().getSimpleName());
        }
    }

    @Override
    public void delete(T entity) {
        log.info("### Removendo {} referência: {} ###", entity.getClass().getSimpleName(), entity);
        if (BooleanUtils.isFalse(getColletionRoot().remove(entity))) {
            throw new RepositoryException(HttpStatus.NOT_FOUND, entity.getClass().getSimpleName() + "  não encontrado.");
        }
    }

    @Override
    public void deleteAll() {
        log.info("### Removendo todos os registros ###");
        getColletionRoot().clear();
    }

    @Override
    public T update(T entity, UUID id) {
        log.info("### Atualizando {} com ID: {} ###", entity.getClass().getSimpleName(), id);
        var toUpdate = findByID(id);
        BeanUtils.copyProperties(entity, toUpdate);
        storageManager.store(toUpdate);
        return toUpdate;
    }

    protected abstract Collection<T> getColletionRoot();

    public T update(T entity) {
        log.info("### Atualizando {} ###", entity.getClass().getSimpleName());
        storageManager.store(entity);
        return entity;
    }
}

