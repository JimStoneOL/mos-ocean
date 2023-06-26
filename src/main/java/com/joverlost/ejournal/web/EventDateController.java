package com.joverlost.ejournal.web;

import com.joverlost.ejournal.dto.EventDTO;
import com.joverlost.ejournal.dto.EventDateDTO;
import com.joverlost.ejournal.service.EventDateService;
import com.joverlost.ejournal.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/event/date")
@CrossOrigin
public class EventDateController {

    @Autowired
    private EventDateService eventDateService;

    @PostMapping("/create")
    public ResponseEntity<Object> createEventDate(@RequestBody EventDateDTO eventDateDTO){
        return new ResponseEntity<>(eventDateService.createEventDate(eventDateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllEventDate(){
        return new ResponseEntity<>(eventDateService.getAllEventDate(), HttpStatus.OK);
    }

    @GetMapping("/get/all/by/event/{id}")
    public ResponseEntity<Object> getAllEventDateByEventId(@PathVariable Long id){
        return new ResponseEntity<>(eventDateService.getAllEventDateByEventId(id), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEventDateById(@PathVariable Long id){
        return new ResponseEntity<>(eventDateService.getById(id), HttpStatus.OK);
    }
}
