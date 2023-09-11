package com.ccs.saladejogos.repositories.impl;

import com.ccs.saladejogos.exceptions.JogadorException;
import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import com.ccs.saladejogos.repositories.rootinstances.JogadorRoot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.microstream.storage.types.StorageManager;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JogadorRepository implements MicroStreamRepository<Jogador> {

    private final StorageManager storageManager;

    public boolean hasDataBase() {
        return storageManager.root() != null;
    }

    private JogadorRoot getRoot() {
        return (JogadorRoot) storageManager.root();
    }

    @Override
    public long store(Jogador entity) {
        log.info("Armazenando Jogador " + entity.getApelido());
        var root = getRoot();
        root.getJogadores().add(entity);

        return storageManager.store(entity);
    }

    @Override
    public Collection<Jogador> findAll() {
        log.info("Recuperando todos os  Jogadores");
        var root = getRoot();

        return root.getJogadores();
    }

    @Override
    public Jogador findByID(UUID id) {
        log.info("Buscando Jogador pelo ID: " + id);

        return getRoot().getJogadores()
                .stream()
                .filter(j -> j.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new JogadorException(HttpStatus.NOT_FOUND, "Jogador não encontrado."));
    }

    @Override
    public void deleteDeleteById(UUID id) {
        var root = getRoot();

        if (!root.getJogadores().remove(findByID(id))) {
            throw new JogadorException("Erro ao remover Jogador");
        }
        log.info("Jogador ID: {} excluído.", id);

        storageManager.store(root.getJogadores());
    }

    @Override
    public void delete(Jogador entity) {
        log.info("Excluindo jogador pela referência");

        var root = getRoot();
        root.getJogadores().remove(entity);

        storageManager.store(root.getJogadores());
    }

    @Override
    public void deleteAll() {
        log.info("Removendo todos os Jogadores");

        var root = getRoot();
        root.getJogadores().clear();
        storageManager.store(root);
    }

    @Override
    public Collection<Jogador> findByArgs(String... args) {
        log.info("Recuperando Jogadores pelos argumentos {}", Arrays.stream(args).toList());

        var argsList = Arrays.stream(args).toList();

        return getRoot().getJogadores().stream().filter(j ->
                        filterByArgs(argsList, j))
                .collect(Collectors.toList());
    }

    @Override
    public Jogador update(Jogador entity) {

        var toUpdate = findByID(entity.getId());

        BeanUtils.copyProperties(entity, toUpdate);

        log.info("Jogador ID: {} atualizado com sucesso.", entity.getId());
        return toUpdate;
    }

    private boolean filterByArgs(List<String> args, Jogador jogador) {
        return args.stream().anyMatch(arg ->
                jogador.getNomeCompleto().contains(arg) || jogador.getApelido().contains(arg));
    }
}
