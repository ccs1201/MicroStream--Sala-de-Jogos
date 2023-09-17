package com.ccs.saladejogos.controllers;

import com.ccs.saladejogos.model.dtos.input.SalaInput;
import com.ccs.saladejogos.model.dtos.output.SalaOutput;
import com.ccs.saladejogos.services.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("api/sala")
@RequiredArgsConstructor
@Tag(name = "Salas")
public class SalaController {

    private final SalaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SalaOutput post(@Valid @RequestBody SalaInput salaInput) {
        return SalaOutput.toOutput(service.save(salaInput.toEntity()));
    }

    @PutMapping("{salaId}")
    @ResponseStatus(HttpStatus.OK)
    SalaOutput update(@PathVariable UUID salaId, @RequestBody SalaInput salaInput) {
        return SalaOutput.toOutput(service.update(salaId, salaInput.toEntity()));
    }

    @DeleteMapping("{salaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable UUID salaId) {
        service.deleteById(salaId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Collection<SalaOutput> getAll() {
        return SalaOutput.toOutput(service.findAll());
    }

    @PatchMapping("/{salaId}/jogador/{jogadorId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Adiciona um jogador a sala", summary = "Adicionar Jogador")
    SalaOutput adiconarJogador(@PathVariable UUID salaId, @PathVariable UUID jogadorId) {
        return SalaOutput
                .toOutput(service.adicionarJogador(salaId, jogadorId));
    }

    @PatchMapping("/{salaId}/aberta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Abre uma sala permitindo assim que jogadores possam ser adicionas", summary = "Abre Sala")
    void abrirSalar(@PathVariable UUID salaId) {
        service.abrirSala(salaId);
    }

    @PatchMapping("/{salaId}/encerrada")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Encerra a Sala e remove todos os jogadores, marca como encerrada e muda " +
            "seu status para fechada", summary = "Encerrar Sala")
    void encerrarSala(@PathVariable UUID salaId) {
        service.encerrarSala(salaId);
    }

    @GetMapping("/{salaId}")
    @ResponseStatus(HttpStatus.OK)
    SalaOutput getById(@PathVariable UUID salaId) {
        return SalaOutput.toOutput(service.fidById(salaId));
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Busca todos os jogadores que coincidirem com pelos menos um dos argumentos (Case Sensitive)",
            summary = "Pesquisa genérica de jogadores")
    Collection<SalaOutput> findByArgs(@RequestParam String... args) {
        return SalaOutput
                .toOutput(service.findByArgs(args));
    }
}
