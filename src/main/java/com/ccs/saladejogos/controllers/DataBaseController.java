package com.ccs.saladejogos.controllers;

import com.ccs.saladejogos.configs.LoadData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/resetdata")
@RequiredArgsConstructor
@Tag(name = "Database")
public class DataBaseController {

    private final LoadData loadData;

    @Operation(description = "Remove todos os registro e popula a base da dados com valores padrão.")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    void resetarBase() {
        CompletableFuture.runAsync(loadData::resetData);
    }
}
