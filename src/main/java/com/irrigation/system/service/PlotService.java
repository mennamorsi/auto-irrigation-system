package com.irrigation.system.service;

import com.irrigation.system.entity.ConfigurationEntity;
import com.irrigation.system.entity.PlotEntity;
import com.irrigation.system.exception.ApiException;
import com.irrigation.system.model.PlotConfiguration;
import com.irrigation.system.repository.ConfigurationRepository;
import com.irrigation.system.repository.PlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.irrigation.system.constants.constant.PLOT_IS_UNIQUE;
import static com.irrigation.system.constants.constant.PLOT_NOT_FOUND;
import static com.irrigation.system.utils.Helper.getNullPropertyNames;

@Service
@Slf4j
public class PlotService {
    @Autowired
    PlotRepository plotRepository;

    @Autowired
    ConfigurationRepository configurationRepository;

    @Autowired
    IrrigationManagementService irrigationManagementService;

    public PlotEntity addNewPlot(PlotEntity plotEntity){
        Optional<PlotEntity> plotFound = plotRepository.findByplotCode(plotEntity.getPlotCode());
        if(plotFound.isPresent()){
            log.error("plot name should be unique");
            throw new ApiException(HttpStatus.BAD_REQUEST, PLOT_IS_UNIQUE);
        }
        return plotRepository.save(plotEntity);
    }

    public List<PlotEntity> getAllPlots(){
        return plotRepository.findAll();
    }

    public PlotEntity updatePlot(PlotEntity plotEntity,Long id){
        Optional<PlotEntity> plotEntityFound = plotRepository.findById(id);
        if(plotEntityFound.isEmpty()){
            log.error("plot with id {} not found",id);
            throw new ApiException(HttpStatus.NOT_FOUND, PLOT_NOT_FOUND);
        }
        PlotEntity plotToUpdate = plotEntityFound.get();
        if(plotEntity.getPlotCode() != null){
            plotEntityFound = plotRepository.findByplotCode(plotEntity.getPlotCode());
            if(plotEntityFound.isPresent() && !plotEntityFound.get().getId().equals(id)){
                log.error("plot code should be unique ");
                throw new ApiException(HttpStatus.BAD_REQUEST, PLOT_IS_UNIQUE);
            }
        }
            BeanUtils.copyProperties(plotEntity, plotToUpdate, getNullPropertyNames(plotEntity));

        return plotRepository.save(plotToUpdate);
    }

    public ConfigurationEntity configurePlot(PlotConfiguration plotConfiguration){
        ConfigurationEntity configurationEntity = plotConfiguration.getConfiguration();
        Long plotId = plotConfiguration.getPlotId();
        Optional<PlotEntity> plotEntityOptional = plotRepository.findById(plotId);
        if(plotEntityOptional.isEmpty()){
            log.error("plot with id {} not found",plotId);
            throw new ApiException(HttpStatus.NOT_FOUND, PLOT_NOT_FOUND);
        }
        configurationEntity.setPlotEntity(plotEntityOptional.get());
        Optional<ConfigurationEntity> configurationEntityOptional = configurationRepository.findByPlotEntity_Id(plotId);
        if(configurationEntityOptional.isPresent()){
            ConfigurationEntity configToUpdate = configurationEntityOptional.get();
            BeanUtils.copyProperties(configurationEntity, configToUpdate, getNullPropertyNames(configurationEntity));
            configurationRepository.save(configToUpdate);
            irrigationManagementService.removeIrrigationJob(String.valueOf(configToUpdate.getCronId()));
            irrigationManagementService.addNewIrrigationJob(configToUpdate);
            return configToUpdate;
        }
        configurationEntity = configurationRepository.save(configurationEntity);
        irrigationManagementService.addNewIrrigationJob(configurationEntity);
        return configurationEntity;

    }



}
