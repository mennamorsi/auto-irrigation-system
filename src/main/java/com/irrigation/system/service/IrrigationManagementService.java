package com.irrigation.system.service;

import com.irrigation.system.entity.ConfigurationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class IrrigationManagementService {
    @Autowired
    TaskSchedulingService taskSchedulingService;

    @Autowired
    IrrigationService irrigationService;

    @Autowired
    ConfigurationService configurationService;

    @EventListener(ApplicationReadyEvent.class)
    public void startIrrigationJobs() {
        List<ConfigurationEntity> configurationEntityList = configurationService.getAllConfigurationDetails();
        for(int i = 0; i < configurationEntityList.size(); i++){
            ConfigurationEntity config = configurationEntityList.get(i);
            UUID cronId = UUID.randomUUID();
            int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
            int numOfDays = config.getTimeSpanInDays();
            String cronExpression = "0 0 0 ? * "+dayOfWeek+"/"+numOfDays;
            irrigationService.setConfigurationEntity(config);
            taskSchedulingService.scheduleATask(String.valueOf(cronId),
                    irrigationService, cronExpression);
            config.setCronId(cronId);
            configurationService.saveNewConfigurationEntity(config);
        }

    }

    public void addNewIrrigationJob(ConfigurationEntity config) {
            UUID cronId = UUID.randomUUID();
            int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
            int numOfDays = config.getTimeSpanInDays();
            String cronExpression = "0 0 0 ? * "+dayOfWeek+"/"+numOfDays;
            irrigationService.setConfigurationEntity(config);
            taskSchedulingService.scheduleATask(String.valueOf(cronId),
                    irrigationService, cronExpression); // 50 * * ? * *
            config.setCronId(cronId);
            configurationService.saveNewConfigurationEntity(config);
        }

    public void removeIrrigationJob(String jobId){
        taskSchedulingService.removeScheduledTask(jobId);
    }

}
