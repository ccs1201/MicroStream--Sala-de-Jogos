package com.ccs.saladejogos.repositories;

import java.util.Collection;
import java.util.UUID;

public interface MicroStreamRepository<T> {

    long store(T entity);

    Collection<T> findAll();

    T findByID(UUID id);

    void deleteDeleteById(UUID id);

    void delete(T entity);

    void deleteAll();

    Collection<T> findByArgs(String... args);

    T update(T entity);

    boolean hasDataBase();
}
