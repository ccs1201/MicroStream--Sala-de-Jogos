package com.ccs.saladejogos.repositories;

import com.ccs.saladejogos.repositories.rootinstances.DataRoot;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import one.microstream.persistence.binary.jdk17.types.BinaryHandlersJDK17;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageFoundation;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

@RequiredArgsConstructor
public abstract class MicroStreamBaseRepository {

    protected final EmbeddedStorageManager storageManager;
    private final EmbeddedStorageFoundation<?> foundation = EmbeddedStorage.Foundation();
    protected DataRoot root;


    @PostConstruct
    private void init() {
        storageManager.setRoot(root);
        foundation.onConnectionFoundation(BinaryHandlersJDK17::registerJDK17TypeHandlers);
    }
}
