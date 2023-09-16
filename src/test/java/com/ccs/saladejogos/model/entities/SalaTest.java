package com.ccs.saladejogos.model.entities;

import com.ccs.saladejogos.exceptions.JogadorJaTemSalaException;
import com.ccs.saladejogos.exceptions.SalaFechadaException;
import com.ccs.saladejogos.exceptions.SalaLotadaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SalaTest {

    private List<Jogador> jogadores;
    private Sala sala;

    @BeforeEach
    void setup() {
        Jogador j1 = Jogador
                .builder()
                .id(UUID.randomUUID())
                .apelido("J_1")
                .nomeCompleto("Jogador 1")
                .build();

        Jogador j2 = Jogador
                .builder()
                .id(UUID.randomUUID())
                .apelido("J_2")
                .nomeCompleto("Jogador 2")
                .build();

        Jogador j3 = Jogador
                .builder()
                .id(UUID.randomUUID())
                .apelido("J_3")
                .nomeCompleto("Jogador 3")
                .build();

        Jogador j4 = Jogador
                .builder()
                .id(UUID.randomUUID())
                .apelido("J_4")
                .nomeCompleto("Jogador 4")
                .build();

        Jogador j5 = Jogador
                .builder()
                .id(UUID.randomUUID())
                .apelido("J_5")
                .nomeCompleto("Jogador 5")
                .build();

        jogadores = List.of(j1, j2, j3, j4, j5);

        sala = Sala.builder()
                .id(UUID.randomUUID())
                .descricao("Teste Sala 1")
                .build();
    }

    @Test
    void addJogadorComSalaFechada() {
        assertThrows(SalaFechadaException.class,
                () -> sala.addJogador(jogadores.get(0)));
    }

    @Test
    void addJogador() {

        buildSala();

        sala.getJogadores().forEach(j -> validarSalaJogador(sala, j));

        var newJogadores = new ArrayList<>(jogadores);
        newJogadores.remove(4);

        assertArrayEquals(newJogadores.toArray(), sala.getJogadores().toArray());
        assertEquals(4, sala.getJogadores().size());
    }

    @Test
    void removeJogador() {

        buildSala();

        sala.removerJogador(jogadores.get(0));

        assertEquals(3, sala.getJogadores().size());
        assertNull(jogadores.get(0).getSala());
    }

    @Test
    void adicionarMaisJogadoresQuePermitido() {
        buildSala();

        assertThrows(SalaLotadaException.class,
                () -> sala.addJogador(jogadores.get(4)));
    }

    @Test
    void adcionarJogadorRepetido() {
        buildSala();

        sala.removerJogador(jogadores.get(3));

        assertThrows(JogadorJaTemSalaException.class, () -> sala.addJogador(jogadores.get(0)));
        assertEquals(3, sala.getJogadores().size());
        assertFalse(sala.getJogadores().contains(jogadores.get(3)));
    }


    @Test
    void adicionarJogadorEmMaisDeUmaSala() {

        buildSala();

        var sala2 = Sala.builder().build();

        sala2.abrirSala();

        assertThrows(JogadorJaTemSalaException.class, () -> sala2.addJogador(jogadores.get(0)));
    }

    @Test
    void encerrarSala() {
        buildSala();

        sala.encerrarSala();

        assertFalse(sala.isAberta());
        assertTrue(sala.isEncerrada());
        assertEquals(4, sala.getJogadores().size());

        jogadores.forEach(j -> validarSalaJogador(null, j));

    }

    @Test
    void adicionarJogarSalaEncerrada() {
        buildSala();

        sala.removerJogador(jogadores.get(0));
        sala.encerrarSala();

        assertThrows(SalaFechadaException.class, () -> sala.addJogador(jogadores.get(0)));
        assertTrue(sala.isEncerrada());
        assertEquals(3, sala.getJogadores().size());
    }

    private void validarSalaJogador(Sala sala, Jogador jogador) {
        assertEquals(sala, jogador.getSala());
    }

    private void buildSala() {
        sala.abrirSala();

        assertDoesNotThrow(() -> sala.addJogador(jogadores.get(0)));
        assertDoesNotThrow(() -> sala.addJogador(jogadores.get(1)));
        assertDoesNotThrow(() -> sala.addJogador(jogadores.get(2)));
        assertDoesNotThrow(() -> sala.addJogador(jogadores.get(3)));
    }
}
