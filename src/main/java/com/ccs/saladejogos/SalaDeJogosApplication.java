package com.ccs.saladejogos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SalaDeJogosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaDeJogosApplication.class, args);
    }

}
