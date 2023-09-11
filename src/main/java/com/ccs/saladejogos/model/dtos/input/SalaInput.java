package com.ccs.saladejogos.model.dtos.input;

import com.ccs.saladejogos.model.entities.Sala;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SalaInput(@NotBlank String descricao) {

    public Sala toEntity() {
        return Sala.builder()
                .id(UUID.randomUUID())
                .aberta(false)
                .encerrada(false)
                .descricao(this.descricao)
                .build();
    }
}
