package com.ccs.saladejogos.model.entities;

import com.ccs.saladejogos.exceptions.JogadorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class JogadorTest {
    private Jogador jogador;
    private Sala sala;

    @BeforeEach
    void setup() {
        jogador = Jogador
                .builder()
                .nomeCompleto("Jogador")
                .apelido("JJ")
                .id(UUID.randomUUID())
                .build();
        sala = Sala.builder()
                .aberta(true)
                .build();
        sala.addJogador(jogador);
    }

    @Test
    void setSala() {
        Assertions.assertThrows(JogadorException.class, () -> jogador.setSala(sala));
    }

    @Test
    void sairDaSala() {
        jogador.sairDaSala();
        Assertions.assertNull(jogador.getSala());
        Assertions.assertFalse(sala.getJogadores().contains(jogador));
    }

    @Test
    void removerDaSala() {
        jogador.removerDaSala();
        Assertions.assertNull(jogador.getSala());
    }
}
