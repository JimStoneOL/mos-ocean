package com.joverlost.ejournal.web;

import com.joverlost.ejournal.dto.EventDateDTO;
import com.joverlost.ejournal.dto.EventTimeDTO;
import com.joverlost.ejournal.service.EventTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/event/time")
@CrossOrigin
public class EventTimeController {

    @Autowired
    private EventTimeService eventTimeService;

    @PostMapping("/create")
    public ResponseEntity<Object> createEventTime(@RequestBody EventTimeDTO eventTimeDTO){
        return new ResponseEntity<>(eventTimeService.createEventTime(eventTimeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllEventTime(){
        return new ResponseEntity<>(eventTimeService.getAllEventTime(), HttpStatus.OK);
    }

    @GetMapping("/get/all/by/event/date/{id}")
    public ResponseEntity<Object> getAllEventTimeByEventDateId(@PathVariable Long id){
        return new ResponseEntity<>(eventTimeService.getAllEventTimeByEventDateId(id), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEventTimeById(@PathVariable Long id){
        return new ResponseEntity<>(eventTimeService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get/detail/{id}")
    public ResponseEntity<Object> getDetailEventTimeById(@PathVariable Long id){
        return new ResponseEntity<>(eventTimeService.getDetailEventById(id), HttpStatus.OK);
    }
}
