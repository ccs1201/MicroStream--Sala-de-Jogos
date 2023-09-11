package com.ccs.saladejogos.configs;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.repositories.MicroStreamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class LoadData {

    private final MicroStreamRepository<Jogador> repository;

    @PostConstruct
    void init() {
        if (repository.findAll().isEmpty())
            popular();
    }

    private void popular() {

        log.info("### Sem dados cadastrados populando....###");

        Jogador j1 = Jogador.builder()
                .id(UUID.fromString("a1789c48-2222-4418-9867-52824342d18e"))
                .apelido("J_1")
                .nomeCompleto("Jogador 1")
                .build();

        Jogador j2 = Jogador.builder()
                .id(UUID.fromString("92ef1b2f-4d49-41b9-8c2e-1d6cf111cac0"))
                .apelido("J_2")
                .nomeCompleto("Jogador 2")
                .build();

        Jogador j3 = Jogador.builder()
                .id(UUID.fromString("bc081fb7-5dae-4276-a688-42a2eafdc101"))
                .apelido("J_3")
                .nomeCompleto("Jogador 3")
                .build();

        Jogador j4 = Jogador.builder()
                .id(UUID.fromString("177a3bd6-da60-4f61-90c0-4c29a3b038f1"))
                .apelido("J_4")
                .nomeCompleto("Jogador 4")
                .build();

        Jogador j5 = Jogador.builder()
                .id(UUID.fromString("669e3e03-e3e7-4cc3-820d-7a6016d6dbf5"))
                .apelido("J_5")
                .nomeCompleto("Jogador 5")
                .build();

        repository.store(j1);
        repository.store(j2);
        repository.store(j3);
        repository.store(j4);
        repository.store(j5);

        log.info("### Dados populados com sucesso ###");
    }
}
