package com.joverlost.ejournal.web;

import com.joverlost.ejournal.dto.EventDTO;
import com.joverlost.ejournal.dto.ExcelDTO;
import com.joverlost.ejournal.dto.FormDTO;
import com.joverlost.ejournal.entity.Event;
import com.joverlost.ejournal.entity.EventDate;
import com.joverlost.ejournal.entity.EventTime;
import com.joverlost.ejournal.entity.Form;
import com.joverlost.ejournal.facade.EventFacade;
import com.joverlost.ejournal.facade.FormFacade;
import com.joverlost.ejournal.service.EventService;
import com.joverlost.ejournal.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/excel")
@CrossOrigin
public class ExcelController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventFacade eventFacade;

    @GetMapping("/get")
    public ResponseEntity<Object> getAllData(){

       List<ExcelDTO> excelDTOList=new ArrayList<>();
       List<EventDTO> eventDTOList=eventService.getAllEvent();
       for(int i=0;i<eventDTOList.size();i++){
         Event event=eventFacade.eventDTOToEvent(eventDTOList.get(i));
         List<EventDate> eventDateList=event.getEventDateList();
         for(int k=0;k<eventDateList.size();k++){
             List<EventTime> eventTimeList=eventDateList.get(k).getEventTimeList();
             for(int l=0;l<eventTimeList.size();l++){
                 List<Form> formList=eventTimeList.get(l).getForms();
                 int amountTickets= eventTimeList.get(l).getAmount();
                 for(int p=0;p<formList.size();p++){
                     Form form=formList.get(p);
                     ExcelDTO excelDTO=new ExcelDTO();
                     excelDTO.setНазвание_мероприятия(event.getName());
                     excelDTO.setДата(eventDateList.get(k).getLocalDate());
                     excelDTO.setВремя(eventTimeList.get(l).getLocalTime());
                     excelDTO.setВсего_билетов(eventTimeList.get(l).getAmount());
                     excelDTO.setЗабронировано(form.getBooking());
                     amountTickets-=form.getBooking();
                     excelDTO.setВсего_осталось_билетов(amountTickets);
                     excelDTO.setФио(form.getLastname() + " " + form.getFirstname()+" "+form.getMiddleName());
                     excelDTO.setДолжность(form.getPosition());
                     excelDTO.setПодразделение(form.getDivision());
                     excelDTO.setEmail(form.getEmail());
                     excelDTO.setОдобрен(form.isAccepted());
                     excelDTOList.add(excelDTO);
                 }
             }
         }
       }
        Collections.sort(excelDTOList);
       return new ResponseEntity<>(excelDTOList,HttpStatus.OK);
    }
}

