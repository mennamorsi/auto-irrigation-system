package com.irrigation.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "plot")
public class PlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "plot_code")
    @NotBlank(message = "Invalid plot code: Empty plot code")
    @NotNull(message = "Invalid plot code: plot code is NULL")
    private String plotCode;
    @Column(name = "plot_length")
    @NotNull(message = "Invalid plot length: plot length is NULL")
    private Long plotLength;
    @Column(name = "plot_width")
    @NotNull(message = "Invalid plot width: plot width is NULL")
    private Long plotWidth;
    @Column(name = "plot_type")
    @NotBlank(message = "Invalid plot type: Empty plot type")
    @NotNull(message = "Invalid plot type: plot type is NULL")
    private String plotType;

}
