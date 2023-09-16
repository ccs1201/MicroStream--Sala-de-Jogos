package com.ccs.saladejogos.repositories;

import com.ccs.saladejogos.repositories.rootinstances.DataRoot;
import jakarta.annotation.PostConstruct;
import one.microstream.persistence.binary.jdk17.types.BinaryHandlersJDK17;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageFoundation;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class MicroStreamBaseRepository {
    @Autowired
    protected EmbeddedStorageManager storageManager;
    @Autowired
    protected DataRoot root;
    private final EmbeddedStorageFoundation<?> foundation = EmbeddedStorage.Foundation();


    @PostConstruct
    private void init() {
        storageManager.setRoot(root);
        foundation.onConnectionFoundation(BinaryHandlersJDK17::registerJDK17TypeHandlers);
    }
}
