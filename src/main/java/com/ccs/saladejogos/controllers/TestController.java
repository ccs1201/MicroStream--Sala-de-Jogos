package com.ccs.saladejogos.controllers;

import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/test")
public class TestController {


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void post() {
        // Initialize a storage manager ("the database") with purely defaults.
        final EmbeddedStorageManager storageManager = EmbeddedStorage.start();

        // print the last loaded root instance,
        // replace it with a current version and store it
        System.out.println(storageManager.root());
        storageManager.setRoot("Hello World! @ " + OffsetDateTime.now());
        storageManager.storeRoot();

        // shutdown storage
        storageManager.shutdown();
    }
}
