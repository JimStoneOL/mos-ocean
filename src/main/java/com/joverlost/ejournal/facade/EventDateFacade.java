package com.joverlost.ejournal.facade;

import com.joverlost.ejournal.dto.EventDateDTO;
import com.joverlost.ejournal.entity.EventDate;
import com.joverlost.ejournal.entity.EventTime;
import com.joverlost.ejournal.repository.EventRepository;
import com.joverlost.ejournal.repository.EventTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventDateFacade {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventTimeRepository eventTimeRepository;

    public EventDateDTO eventDateToEventDateDTO(EventDate eventDate){
        EventDateDTO eventDateDTO=new EventDateDTO();
        eventDateDTO.setId(eventDate.getId());
        eventDateDTO.setLocalDate(eventDate.getLocalDate());
        eventDateDTO.setEventId(eventDate.getEvent().getId());
        if(eventDate.getEventTimeList()==null){
            eventDateDTO.setEventTimeListId(null);
        }else{
            List<Long> eventTimeListId=new ArrayList<>();
            for(int i=0;i<eventDate.getEventTimeList().size();i++){
                eventTimeListId.add(eventDate.getEventTimeList().get(i).getId());
            }
            eventDateDTO.setEventTimeListId(eventTimeListId);
        }
        return eventDateDTO;
    }

    public EventDate eventDateDTOToEventDate(EventDateDTO eventDateDTO){
        EventDate eventDate=new EventDate();
        eventDate.setId(eventDateDTO.getId());
        eventDate.setLocalDate(eventDateDTO.getLocalDate());
        eventDate.setEvent(eventRepository.findById(eventDateDTO.getEventId()).get());
        if(eventDateDTO.getEventTimeListId()==null){
            eventDate.setEventTimeList(null);
        }else{
            List<EventTime> eventTimeList=new ArrayList<>();
            for(int i=0;i<eventDateDTO.getEventTimeListId().size();i++){
                eventTimeList.add(eventTimeRepository.findById(eventDateDTO.getEventTimeListId().get(i)).get());
            }
            eventDate.setEventTimeList(eventTimeList);
        }
        return eventDate;
    }

    public List<EventDateDTO> eventDateListToEventDateDTOList(List<EventDate> eventDateList){
        List<EventDateDTO> eventDateDTOList=new ArrayList<>();
        for(int i=0;i<eventDateList.size();i++){
            eventDateDTOList.add(eventDateToEventDateDTO(eventDateList.get(i)));
        }
        return eventDateDTOList;
    }
}
