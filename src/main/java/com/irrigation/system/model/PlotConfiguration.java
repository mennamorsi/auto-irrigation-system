package com.irrigation.system.model;

import com.irrigation.system.entity.ConfigurationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.module.Configuration;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class PlotConfiguration {
    @NotNull(message = "Invalid plot id: plot id is NULL")
    private Long plotId;
    @Valid
    private ConfigurationEntity configuration;
}
