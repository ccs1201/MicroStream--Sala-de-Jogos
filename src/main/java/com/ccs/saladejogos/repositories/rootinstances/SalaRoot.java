package com.ccs.saladejogos.repositories.rootinstances;

import com.ccs.saladejogos.model.entities.Sala;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Getter
public class SalaRoot {
    private final Collection<Sala> salas = new ArrayList<>();

}
