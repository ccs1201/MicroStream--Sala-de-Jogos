package com.ccs.saladejogos.controllers;

import com.ccs.saladejogos.model.entities.Jogador;
import com.ccs.saladejogos.model.entities.dtos.input.JogadorInput;
import com.ccs.saladejogos.services.JogadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/jogador")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long postJogador(@RequestBody JogadorInput jogador) {
        return service.store(jogador.toEntity());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Collection<Jogador> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Jogador getByObjectId(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/args")
    @ResponseStatus(HttpStatus.OK)
    Collection<Jogador> findByArgs(@RequestParam String... args) {
        return service.findByArgs(args);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Jogador update(@PathVariable UUID id, @Valid @RequestBody JogadorInput jogadorInput) {
        return service.update(jogadorInput.toEntity(), id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id){
        service.delete(id);
    }
}
