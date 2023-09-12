package com.ccs.saladejogos.repositories.impl;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.repositories.JogadorRepository;
import lombok.extern.slf4j.Slf4j;
import one.microstream.storage.types.StorageManager;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class JogadorRepositoryImpl extends MicroStreamRepositoryImpl<Jogador> implements JogadorRepository {
    public JogadorRepositoryImpl(StorageManager storageManager) {
        super(storageManager);
    }

    @Override
    public Collection<Jogador> findByArgs(String... args) {
        log.info("Recuperando Jogadores pelos argumentos {}", Arrays.stream(args).toList());

        var argsList = Arrays.stream(args).toList();

        return getColletionRoot()
                .stream()
                .filter(jogadorNoFilter ->
                        filterByArgs(argsList, jogadorNoFilter))
                .collect(Collectors.toList());
    }

    @Override
    protected Collection<Jogador> getColletionRoot() {
        return super.getRoot().getJogadores();
    }

    private boolean filterByArgs(List<String> args, Jogador jogador) {
        return args
                .stream()
                .anyMatch(arg ->
                        jogador.getNomeCompleto().contains(arg) || jogador.getApelido().contains(arg));
    }
}
