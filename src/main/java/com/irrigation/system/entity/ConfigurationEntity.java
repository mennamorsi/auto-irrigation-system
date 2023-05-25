package com.irrigation.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "configuration")
public class ConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plot_id", referencedColumnName = "id")
    private PlotEntity plotEntity;
    @Column(name = "time_span_in_days")
    @Min(value=1, message="number of days: positive number, min 1 is required")
    @Max(value=6, message="number of days: max 6 is required")
    private Integer timeSpanInDays;

    @Column(name = "number_of_blocked_retries")
    private Integer numberOfBlockedRetries;

    @Column(name = "retry_flag")
    private Boolean retryFlag;

    @Column(name = "number_of_retry")
    private Integer numberOfRetry;

    @Column(name = "cron_id")
    private UUID cronId;
}
