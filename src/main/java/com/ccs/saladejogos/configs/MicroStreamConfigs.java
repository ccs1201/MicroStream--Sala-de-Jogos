package com.ccs.saladejogos.configs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("microstream")
public class MicroStreamConfigs {

    @NotBlank
    private String storage_Directory;

    @Min(4)
    @NotNull
    private Integer channel_Count;
}
