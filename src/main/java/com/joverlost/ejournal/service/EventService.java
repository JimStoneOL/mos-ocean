package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.EventDTO;
import com.joverlost.ejournal.entity.Event;
import com.joverlost.ejournal.entity.Form;
import com.joverlost.ejournal.exception.EventNotFoundException;
import com.joverlost.ejournal.facade.EventFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.EventRepository;
import com.joverlost.ejournal.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService{
    private final EventRepository eventRepository;
    private final FormRepository formRepository;
    private final EventFacade eventFacade;

    @Autowired
    public EventService(EventRepository eventRepository, FormRepository formRepository, EventFacade eventFacade) {
        this.eventRepository = eventRepository;
        this.formRepository = formRepository;
        this.eventFacade = eventFacade;
    }

    public MessageResponse createEvent(EventDTO eventDTO){
        eventRepository.save(eventFacade.eventDTOToEvent(eventDTO));
        return new MessageResponse("Успешно создано");
    }

    public List<EventDTO> getAllEvent(){
        return eventFacade.eventListToEventDTOList(eventRepository.findAll());
    }

    public EventDTO getById(Long id){
        return eventFacade.eventToEventDTO(eventRepository.findById(id).orElseThrow(()->new EventNotFoundException()));
    }

//    public MessageResponse deleteEvent(Long id){
//        Event event=eventFacade.eventDTOToEvent(getById(id));
//        List<Form> formList=event.getForms();
//        for(int i=0;i<formList.size();i++){
//            formRepository.deleteById(formList.get(i).getId());
//        }
//        eventRepository.deleteById(id);
//        return new MessageResponse("Успешно удалено");
//    }
}
