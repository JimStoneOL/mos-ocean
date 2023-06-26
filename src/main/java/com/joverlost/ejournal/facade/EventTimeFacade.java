package com.joverlost.ejournal.facade;

import com.joverlost.ejournal.dto.EventDateDTO;
import com.joverlost.ejournal.dto.EventTimeDTO;
import com.joverlost.ejournal.entity.EventDate;
import com.joverlost.ejournal.entity.EventTime;
import com.joverlost.ejournal.entity.Form;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.EventDateRepository;
import com.joverlost.ejournal.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventTimeFacade {

    @Autowired
    private EventDateRepository eventDateRepository;
    @Autowired
    private FormRepository formRepository;

    public EventTimeDTO eventTimeToEventTimeDTO(EventTime eventTime){
        EventTimeDTO eventTimeDTO=new EventTimeDTO();
        eventTimeDTO.setId(eventTime.getId());
        eventTimeDTO.setLocalTime(eventTime.getLocalTime());
        eventTimeDTO.setAmount(eventTime.getAmount());
        eventTimeDTO.setEventDateId(eventTime.getEventDate().getId());
        if(eventTime.getForms()==null){
            eventTimeDTO.setFormListId(null);
        }else{
            List<Long> formListId=new ArrayList<>();
            for(int i=0;i<eventTime.getForms().size();i++){
                formListId.add(eventTime.getForms().get(i).getId());
            }
            eventTimeDTO.setFormListId(formListId);
        }

        eventTimeDTO.setRestBooking(getDetailEventById(eventTime));
        return eventTimeDTO;
    }

    public EventTime eventTimeDTOToEventTime(EventTimeDTO eventTimeDTO){
        EventTime eventTime=new EventTime();
        eventTime.setId(eventTimeDTO.getId());
        eventTime.setAmount(eventTimeDTO.getAmount());
        eventTime.setLocalTime(eventTimeDTO.getLocalTime());
        eventTime.setEventDate(eventDateRepository.findById(eventTimeDTO.getEventDateId()).get());
        if(eventTimeDTO.getFormListId()==null){
            eventTime.setForms(null);
        }else{
            List<Form> formList=new ArrayList<>();
            for(int i=0;i<eventTimeDTO.getFormListId().size();i++){
                formList.add(formRepository.findById(eventTimeDTO.getFormListId().get(i)).get());
            }
            eventTime.setForms(formList);
        }
        return eventTime;
    }

    public List<EventTimeDTO> eventTimeListToEventTimeDTOList(List<EventTime> eventTimeList){
        List<EventTimeDTO> eventTimeDTOList=new ArrayList<>();
        for(int i=0;i<eventTimeList.size();i++){
            eventTimeDTOList.add(eventTimeToEventTimeDTO(eventTimeList.get(i)));
        }
        return eventTimeDTOList;
    }

    private int getDetailEventById(EventTime eventTime){
        int amount=eventTime.getAmount();
        List<Form> formList=eventTime.getForms();
        int sumBooking=0;
        for(int i=0;i<formList.size();i++){
            sumBooking+=formList.get(i).getBooking();
        }
        return (amount-sumBooking);
    }
}
