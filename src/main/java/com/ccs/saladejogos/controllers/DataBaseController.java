package com.ccs.saladejogos.controllers;

import com.ccs.saladejogos.configs.LoadData;
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
public class DataBaseController {

    private final LoadData loadData;

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    void resetarBase() {
        CompletableFuture.runAsync(loadData::resetData);
    }
}
