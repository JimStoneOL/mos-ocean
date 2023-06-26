package com.joverlost.ejournal.facade;

import com.joverlost.ejournal.dto.EventDTO;
import com.joverlost.ejournal.entity.Event;
import com.joverlost.ejournal.entity.EventDate;
import com.joverlost.ejournal.entity.Form;
import com.joverlost.ejournal.repository.EventDateRepository;
import com.joverlost.ejournal.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventFacade {

    @Autowired
    private EventDateRepository eventDateRepository;

    public EventDTO eventToEventDTO(Event event){
        EventDTO eventDTO=new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        if(event.getEventDateList()==null){
            eventDTO.setEventDateListId(null);
        }else{
            List<Long> eventDateListId=new ArrayList<>();
            for(int i=0;i<event.getEventDateList().size();i++){
                eventDateListId.add(event.getEventDateList().get(i).getId());
            }
            eventDTO.setEventDateListId(eventDateListId);
        }
        return eventDTO;
    }

    public Event eventDTOToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        if(eventDTO.getEventDateListId()==null){
            event.setEventDateList(null);
        }else{
            List<EventDate> eventDateList = new ArrayList<>();
            for (int i = 0; i < eventDTO.getEventDateListId().size(); i++) {
                eventDateList.add(eventDateRepository.findById(eventDTO.getEventDateListId().get(i)).get());
            }
            event.setEventDateList(eventDateList);
        }
        return event;
    }

    public List<EventDTO> eventListToEventDTOList(List<Event> eventList){
        List<EventDTO> eventDTOList=new ArrayList<>();
        for(int i=0;i<eventList.size();i++){
            eventDTOList.add(eventToEventDTO(eventList.get(i)));
        }
        return eventDTOList;
    }

    }
//@Autowired
//    private FormRepository formRepository;
//
//    public EventDTO eventToEventDTO(Event event){
//        EventDTO eventDTO=new EventDTO();
//        eventDTO.setId(event.getId());
//        eventDTO.setName(event.getName());
//        eventDTO.setAmount(event.getAmount());
//        eventDTO.setDate(event.getDate());
//
//        if(event.getForms()==null){
//            eventDTO.setFormListId(null);
//        }else{
//            List<Long> formListId=new ArrayList<>();
//            for(int i=0;i<event.getForms().size();i++){
//                formListId.add(event.getForms().get(i).getId());
//            }
//            eventDTO.setFormListId(formListId);
//        }
//        return eventDTO;
//    }
//
//    public Event eventDTOToEvent(EventDTO eventDTO){
//        Event event=new Event();
//        event.setId(eventDTO.getId());
//        event.setName(eventDTO.getName());
//        event.setAmount(eventDTO.getAmount());
//        event.setDate(eventDTO.getDate());
//        if(eventDTO.getFormListId()==null){
//            event.setForms(null);
//        }else{
//            List<Form> formList=new ArrayList<>();
//            for(int i=0;i<eventDTO.getFormListId().size();i++){
//                formList.add(formRepository.findById(eventDTO.getFormListId().get(i)).get());
//            }
//            event.setForms(formList);
//        }
//
//        return event;
//    }
//
//    public List<EventDTO> eventListToEventDTOList(List<Event> eventList){
//        List<EventDTO> eventDTOList=new ArrayList<>();
//        for(int i=0;i<eventList.size();i++){
//            eventDTOList.add(eventToEventDTO(eventList.get(i)));
//        }
//        return eventDTOList;
//    }
