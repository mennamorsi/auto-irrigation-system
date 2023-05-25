package com.irrigation.system.controller;

import com.irrigation.system.entity.ConfigurationEntity;
import com.irrigation.system.entity.PlotEntity;
import com.irrigation.system.model.PlotConfiguration;
import com.irrigation.system.service.PlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class PlotController {
    @Autowired
    PlotService plotService;

    @GetMapping("/hello-world")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/plot")
    public ResponseEntity<PlotEntity> AddNewPlot(@Valid @RequestBody PlotEntity plotEntity){
        PlotEntity plot = plotService.addNewPlot(plotEntity);
        return new ResponseEntity<>(plot,HttpStatus.OK);
    }

    @PostMapping("/plotConfigure")
    public ResponseEntity<ConfigurationEntity> configurePlot(@Valid @RequestBody PlotConfiguration plotConfiguration){
        ConfigurationEntity configurationEntity = plotService.configurePlot(plotConfiguration);
        return new ResponseEntity<>(configurationEntity,HttpStatus.OK);
    }

    @PutMapping("/plot/{plotId}")
    public ResponseEntity<PlotEntity> updatePlot(@Valid @RequestBody PlotEntity plotEntity,@Valid @PathVariable Long plotId){
        PlotEntity plot = plotService.updatePlot(plotEntity,plotId);
        return new ResponseEntity<>(plot,HttpStatus.OK);
    }

    @GetMapping("/plots")
    public ResponseEntity<List<PlotEntity>> getAllPlotsDetails(){
        List<PlotEntity> plotEntityList = plotService.getAllPlots();
        return new ResponseEntity<>(plotEntityList,HttpStatus.OK);
    }


}
