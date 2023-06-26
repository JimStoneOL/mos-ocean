package com.joverlost.ejournal.web;

import com.joverlost.ejournal.dto.EventDTO;
import com.joverlost.ejournal.entity.Event;
import com.joverlost.ejournal.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/event")
@CrossOrigin
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<Object> createEvent(@RequestBody EventDTO eventDTO){
        return new ResponseEntity<>(eventService.createEvent(eventDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllEvent(){
        return new ResponseEntity<>(eventService.getAllEvent(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable Long id){
        return new ResponseEntity<>(eventService.getById(id), HttpStatus.OK);
    }

//    @PostMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteEvent(@PathVariable Long id){
//        return new ResponseEntity<>(eventService.deleteEvent(id), HttpStatus.OK);
//    }
}
