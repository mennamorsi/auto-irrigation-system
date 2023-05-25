package com.irrigation.system.service;


import com.irrigation.system.entity.ConfigurationEntity;
import com.irrigation.system.repository.ConfigurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConfigurationService {
    @Autowired
    ConfigurationRepository configurationRepository;
    public List<ConfigurationEntity> getAllConfigurationDetails(){
        return configurationRepository.findAll();
    }
    public  ConfigurationEntity saveNewConfigurationEntity(ConfigurationEntity configurationEntity){ return configurationRepository.save(configurationEntity); }
}
