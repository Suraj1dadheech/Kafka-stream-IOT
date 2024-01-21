package com.pdp.MongoApp.controller;


import com.pdp.MongoApp.collection.Health;
import com.pdp.MongoApp.service.IotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IotController {


    @Autowired
    private IotService iotService;

    @GetMapping("/basic/health")
    public ResponseEntity<List<Health>> getHealthByStepsAndHeartRate(
                @RequestParam(name="steps",required = true) Integer steps,
                @RequestParam(name="heartRate",required = true) Integer heartRate
    ){
        return iotService.getHealthByStepsAndHeartRate(steps,heartRate);
    }



}
