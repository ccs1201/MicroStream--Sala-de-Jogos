package com.ccs.saladejogos.model.entities.dtos.input;

import com.ccs.saladejogos.model.entities.Jogador;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record JogadorInput(
        @NotBlank String nomeCompleto, @NotBlank String apelido) {
    public Jogador toEntity() {
        return Jogador.builder()
                .id(UUID.randomUUID())
                .apelido(this.apelido)
                .nomeCompleto(this.nomeCompleto)
                .build();
    }
}
