package com.ccs.saladejogos.controllers;

import com.ccs.saladejogos.model.dtos.input.SalaInput;
import com.ccs.saladejogos.model.dtos.output.SalaOutput;
import com.ccs.saladejogos.services.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("api/sala")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    long post(@Valid @RequestBody SalaInput salaInput) {

        return service.save(salaInput.toEntity());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Collection<SalaOutput> getAll() {
        return SalaOutput.toCollection(service.findAll());
    }

    @PatchMapping("/{sala_Id}/jogador/{jogador_Id}")
    @ResponseStatus(HttpStatus.OK)
    SalaOutput adiconarJogador(@PathVariable UUID sala_Id, @PathVariable UUID jogador_Id) {
        return SalaOutput
                .toOutput(service.adicionarJogador(sala_Id, jogador_Id));
    }

    @PatchMapping("/{sala_Id}/aberta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void abrirSalar(@PathVariable UUID sala_Id) {
        service.abrirSala(sala_Id);
    }

    @PatchMapping("/{sala_Id}/encerrada")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void encerrarSala(@PathVariable UUID sala_Id) {
        service.encerrarSala(sala_Id);
    }
}
