package com.ccs.saladejogos.controllers;

import com.ccs.saladejogos.model.dtos.input.SalaInput;
import com.ccs.saladejogos.model.entities.Sala;
import com.ccs.saladejogos.services.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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
    Collection<Sala> getAll(){
        return service.findAll();
    }
}
