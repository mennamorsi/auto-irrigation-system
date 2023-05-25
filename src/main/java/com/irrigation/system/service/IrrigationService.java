package com.irrigation.system.service;

import com.irrigation.system.entity.ConfigurationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IrrigationService implements Runnable{
    ConfigurationEntity configurationEntity;

    @Override
    public void run() {
        if(configurationEntity.getNumberOfBlockedRetries() == 0){
            log.info("irrigation for plot code {} is finished successfully",configurationEntity.getPlotEntity().getPlotCode());
        }
        else if(configurationEntity.getNumberOfBlockedRetries() != 0 && configurationEntity.getRetryFlag()){
            log.error("sensor device for plot code {} is unavailable",configurationEntity.getPlotEntity().getPlotCode());
            if(configurationEntity.getNumberOfRetry() != null){
                for(int i=1;i<=configurationEntity.getNumberOfRetry() ; i++){
                    log.info("trying to call sensor device for plot code {}",configurationEntity.getPlotEntity().getPlotCode());
                    if(i > configurationEntity.getNumberOfBlockedRetries()){
                        log.info("irrigation for plot code {} is finished successfully",configurationEntity.getPlotEntity().getPlotCode());
                        break;
                    }else{
                        log.error("sensor device for plot code {} is still unavailable",configurationEntity.getPlotEntity().getPlotCode());
                    }
                }
                if( configurationEntity.getNumberOfRetry() <= configurationEntity.getNumberOfBlockedRetries()){
                    log.error("Failed to irrigate plot code {}",configurationEntity.getPlotEntity().getPlotCode());
                }

            }
        }
    }

    public ConfigurationEntity getConfigurationEntity() {
        return configurationEntity;
    }

    public void setConfigurationEntity(ConfigurationEntity configurationEntity) {
        this.configurationEntity = configurationEntity;
    }
}
