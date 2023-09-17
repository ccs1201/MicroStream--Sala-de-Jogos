package com.ccs.saladejogos.model.dtos.output;

import com.ccs.saladejogos.model.entities.Sala;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public record SalaOutput(UUID id,
                         Collection<JogadorOutput> jogadores,
                         String descricao,
                         Boolean aberta,
                         Boolean encerrada) {

    public static SalaOutput toOutput(Sala sala) {

        return new SalaOutput(sala.getId(),
                Objects.isNull(sala.getJogadores()) ? List.of() : JogadorOutput.toOutput(sala.getJogadores()),
                sala.getDescricao(),
                sala.isAberta(),
                sala.isEncerrada()
        );
    }

    public static Collection<SalaOutput> toOutput(Collection<Sala> salas) {

        return salas.stream().map(SalaOutput::toOutput).collect(Collectors.toList());
    }
}
