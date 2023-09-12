package com.ccs.saladejogos.configs;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.repositories.JogadorRepository;
import com.ccs.saladejogos.repositories.SalaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class LoadData {

    private final JogadorRepository jogadorRepository;
    private final SalaRepository salaRepository;

    @PostConstruct
    void init() {
        if (jogadorRepository.findAll().isEmpty() && salaRepository.findAll().isEmpty())
            popular();
    }

    public void resetData() {
        jogadorRepository.deleteAll();
        salaRepository.deleteAll();
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

        var s1 = Sala.builder()
                .id(UUID.fromString("bd2550e2-8237-472e-b2ac-872f1c04ddbe"))
                .descricao("Sala 1")
                .build();

        var s2 = Sala.builder()
                .id(UUID.fromString("6b7a5116-8d74-4c1b-9780-22ac901929e8"))
                .descricao("Sala 2")
                .build();

        var s3 = Sala.builder()
                .id(UUID.fromString("877816db-4d9c-4352-a701-b5e5ba5daf85"))
                .descricao("Sala 3")
                .build();

        salaRepository.save(s1);
        salaRepository.save(s2);
        salaRepository.save(s3);

        jogadorRepository.save(j1);
        jogadorRepository.save(j2);
        jogadorRepository.save(j3);
        jogadorRepository.save(j4);
        jogadorRepository.save(j5);

        log.info("### Dados populados com sucesso ###");
    }
}
