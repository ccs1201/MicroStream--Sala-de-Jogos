package com.ccs.saladejogos.model.dtos.output;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.model.entities.Sala;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record JogadorOutput(UUID id,
                            String nomeCompleto,
                            String apelido,
                            salaInternal sala) {

    public static JogadorOutput toOutput(Jogador jogador) {

        return new JogadorOutput(jogador.getId(), jogador.getNomeCompleto(), jogador.getApelido(),
                buildSala(jogador.getSala()));
    }

    public static List<JogadorOutput> toOutput(Collection<Jogador> jogadores) {
        return jogadores
                .stream()
                .map(jogador -> new JogadorOutput(jogador.getId(), jogador.getNomeCompleto(), jogador.getApelido(),
                        buildSala(jogador.getSala())))
                .toList();
    }

    private static salaInternal buildSala(Sala sala) {
        return Objects.isNull(sala) ? new salaInternal(null, null) :
                new salaInternal(sala.getId(), sala.getDescricao());
    }

    record salaInternal(UUID id, String descricao) {

    }
}
