package com.ccs.saladejogos.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@EqualsAndHashCode(of = {"id"})
public class SalaJogosEntity {
    UUID id;
}
