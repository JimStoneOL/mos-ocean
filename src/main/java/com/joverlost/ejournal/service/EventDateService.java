package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.EventDateDTO;
import com.joverlost.ejournal.entity.Event;
import com.joverlost.ejournal.exception.EventNotFoundException;
import com.joverlost.ejournal.facade.EventDateFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.EventDateRepository;
import com.joverlost.ejournal.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventDateService {

    private final EventDateRepository eventDateRepository;
    private final EventDateFacade eventDateFacade;
    private final EventRepository eventRepository;

    @Autowired
    public EventDateService(EventDateRepository eventDateRepository, EventDateFacade eventDateFacade, EventRepository eventRepository) {
        this.eventDateRepository = eventDateRepository;
        this.eventDateFacade = eventDateFacade;
        this.eventRepository = eventRepository;
    }

    public MessageResponse createEventDate(EventDateDTO eventDateDTO){
        eventDateRepository.save(eventDateFacade.eventDateDTOToEventDate(eventDateDTO));
        return new MessageResponse("Успешно");
    }

    public List<EventDateDTO> getAllEventDate(){
        return eventDateFacade.eventDateListToEventDateDTOList(eventDateRepository.findAll());
    }

    public List<EventDateDTO> getAllEventDateByEventId(Long id){
        Event event=eventRepository.findById(id).orElseThrow(()->new EventNotFoundException());
        return eventDateFacade.eventDateListToEventDateDTOList(event.getEventDateList());
    }

    public EventDateDTO getById(Long id){
        return eventDateFacade.eventDateToEventDateDTO(eventDateRepository.findById(id).orElseThrow(()->new EventNotFoundException()));
    }
}
