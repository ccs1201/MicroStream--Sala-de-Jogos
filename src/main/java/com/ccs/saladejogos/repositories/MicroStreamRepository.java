package com.ccs.saladejogos.repositories;

import com.ccs.saladejogos.model.entities.SalaJogosEntity;

import java.util.Collection;
import java.util.UUID;

public interface MicroStreamRepository<T extends SalaJogosEntity> {

    T save(T entity);

    Collection<T> findAll();

    T findByID(UUID id);

    void deleteById(UUID id);

    void delete(T entity);

    void deleteAll();

    Collection<T> findByArgs(String... args);

    T update(T entity, UUID id);

    T update(T entity);
}
