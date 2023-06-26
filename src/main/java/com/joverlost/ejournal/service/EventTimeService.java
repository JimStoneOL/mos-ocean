package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.EventDateDTO;
import com.joverlost.ejournal.dto.EventTimeDTO;
import com.joverlost.ejournal.dto.FormDTO;
import com.joverlost.ejournal.entity.EventDate;
import com.joverlost.ejournal.entity.EventTime;
import com.joverlost.ejournal.entity.Form;
import com.joverlost.ejournal.exception.EventNotFoundException;
import com.joverlost.ejournal.facade.EventTimeFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.EventDateRepository;
import com.joverlost.ejournal.repository.EventTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTimeService {

    private final EventTimeRepository eventTimeRepository;
    private final EventTimeFacade eventTimeFacade;
    private final EventDateRepository eventDateRepository;

    @Autowired
    public EventTimeService(EventTimeRepository eventTimeRepository, EventTimeFacade eventTimeFacade, EventDateRepository eventDateRepository) {
        this.eventTimeRepository = eventTimeRepository;
        this.eventTimeFacade = eventTimeFacade;
        this.eventDateRepository = eventDateRepository;
    }

    public MessageResponse createEventTime(EventTimeDTO eventTimeDTO){
        eventTimeRepository.save(eventTimeFacade.eventTimeDTOToEventTime(eventTimeDTO));
        return new MessageResponse("Успешно");
    }

    public List<EventTimeDTO> getAllEventTime(){
        return eventTimeFacade.eventTimeListToEventTimeDTOList(eventTimeRepository.findAll());
    }

    public EventTimeDTO getById(Long id){
        return eventTimeFacade.eventTimeToEventTimeDTO(eventTimeRepository.findById(id).orElseThrow(()->new EventNotFoundException()));
    }

    public MessageResponse getDetailEventById(Long id){
        EventTimeDTO eventTimeDTO=getById(id);
        EventTime eventTime=eventTimeFacade.eventTimeDTOToEventTime(eventTimeDTO);
        int amount=eventTime.getAmount();
        List<Form> formList=eventTime.getForms();
        int sumBooking=0;
        for(int i=0;i<formList.size();i++){
            sumBooking+=formList.get(i).getBooking();
        }
            return new MessageResponse("Доступно мест "+(amount-sumBooking));
    }

    public List<EventTimeDTO> getAllEventTimeByEventDateId(Long id){
        EventDate eventDate=eventDateRepository.findById(id).orElseThrow(()->new EventNotFoundException());
        return eventTimeFacade.eventTimeListToEventTimeDTOList(eventDate.getEventTimeList());
    }
}
