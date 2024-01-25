package com.pdp.MongoApp.controller;


import com.pdp.MongoApp.collection.Customer;
import com.pdp.MongoApp.collection.Health;
import com.pdp.MongoApp.service.IotService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


    @GetMapping("/basic/aggregation")
    public ResponseEntity<List<Document>> getMaxTempratureOfEachDevice(){
        return iotService.getMaxTemperatureOfEveryDevice();
    }

    @GetMapping("/basic/aggregation/calorie")
    public ResponseEntity<List<Document>> getAvgCalorieBurnt(
            @RequestParam("startTimestamp") String startDateTime,
            @RequestParam("endTimestamp") String endDateTime){

        return iotService.getMaximumCaloriesBurned(startDateTime,endDateTime);
    }

    @GetMapping(value = "/reactive/health",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Health> findAllHealth(){
        Flux<Health> flux = iotService.findAll();
        flux = flux.timeout(Duration.ofMinutes(5));
        return flux;
    }

//    @GetMapping("/basic/reactive/customer")
//    @GetMapping(value = "/reactive/customer",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Customer> findAllHealth1(){
//        return  Flux.range(1,50)
//                .delayElements(Duration.ofSeconds(1))
//                .doOnNext(i -> System.out.println("processing........."))
//                .map(i-> new Customer(i,"customer " +i));
//    }




}
