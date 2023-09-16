package com.ccs.saladejogos.model.entities;

import com.ccs.saladejogos.exceptions.JogadorJaTemSalaException;
import com.ccs.saladejogos.exceptions.SalaFechadaException;
import com.ccs.saladejogos.exceptions.SalaLotadaException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.LinkedHashSet;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true, of = {"descricao"})
public class Sala extends SalaJogosEntity {

    private static final Short MAX_JOGADORES = 4;
    private Collection<Jogador> jogadores;
    private String descricao;
    private boolean aberta;
    private boolean encerrada;

    public Boolean isLotada() {
        return jogadores.size() >= MAX_JOGADORES;
    }

    public void abrirSala() {
        aberta = true;
    }

    public void encerrarSala() {
        aberta = false;
        encerrada = true;
        if (ObjectUtils.isNotEmpty(jogadores)) {
            jogadores.forEach(Jogador::removerSala);
        }
    }

    public void addJogador(Jogador jogador) {
        if (isEncerrada()) {
            throw new SalaFechadaException(HttpStatus.BAD_REQUEST, "Sala Encerrada.");
        }
        if (!aberta) {
            throw new SalaFechadaException(HttpStatus.PRECONDITION_REQUIRED, "Não é possível adicionar jogadores a uma Sala fechada.");
        }

        if (jogadores == null) {
            jogadores = new LinkedHashSet<>();
        }

        if (isLotada()) {
            throw new SalaLotadaException(HttpStatus.BAD_REQUEST, "Sala atingiu o limite de jogadores. Limite = " + MAX_JOGADORES);
        }

        if (ObjectUtils.isEmpty(jogador.getSala())) {
            jogador.setSala(this);
            jogadores.add(jogador);
        } else {
          throw new JogadorJaTemSalaException(HttpStatus.CONFLICT, jogador);
        }
    }

    public void removerJogador(Jogador jogador) {
        if (jogadores != null) {
            if (jogadores.remove(jogador)) {
                jogador.removerSala();
            }
        }
    }
}



