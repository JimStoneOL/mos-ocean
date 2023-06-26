package com.joverlost.ejournal.service;

import com.joverlost.ejournal.dto.FormDTO;
import com.joverlost.ejournal.entity.EventTime;
import com.joverlost.ejournal.entity.Form;
import com.joverlost.ejournal.exception.EventNotFoundException;
import com.joverlost.ejournal.exception.FormNotFoundException;
import com.joverlost.ejournal.facade.FormFacade;
import com.joverlost.ejournal.payload.response.MessageResponse;
import com.joverlost.ejournal.repository.EventTimeRepository;
import com.joverlost.ejournal.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    private final FormRepository formRepository;
    private final FormFacade formFacade;
    private final EventTimeRepository eventTimeRepository;
    private final EmailSenderService senderService;

    @Autowired
    public FormService(FormRepository formRepository, FormFacade formFacade, EventTimeRepository eventTimeRepository, EmailSenderService senderService) {
        this.formRepository = formRepository;
        this.formFacade = formFacade;
        this.eventTimeRepository = eventTimeRepository;
        this.senderService = senderService;
    }


    public MessageResponse createForm(FormDTO formDTO){
        EventTime eventTime=formFacade.formDTOToForm(formDTO).getEventTime();
        int amount=eventTime.getAmount();
        List<Form> formList=eventTime.getForms();
        int sumBooking=0;
        for(int i=0;i<formList.size();i++){
            sumBooking+=formList.get(i).getBooking();
        }
        if((sumBooking+formDTO.getBooking())>amount){
            return new MessageResponse("Все места заняты. Доступно мест "+(amount-sumBooking));
        }
        formDTO.setAccepted(false);
        formRepository.save(formFacade.formDTOToForm(formDTO));
        return new MessageResponse("Успешно");
    }

    public List<FormDTO> getAllForms(){
        return formFacade.formListToFormDTOList(formRepository.findAll());
    }

    public List<FormDTO> getAllFormsByEventId(Long id){
         EventTime eventTime=eventTimeRepository.findById(id).orElseThrow(()->new EventNotFoundException());
         return formFacade.formListToFormDTOList(eventTime.getForms());
    }

    public FormDTO getFormById(Long id){
        return formFacade.formToFormDTO(formRepository.findById(id).orElseThrow(()->new FormNotFoundException()));
    }

//    public MessageResponse deleteForm(Long id){
//        Form form=formFacade.formDTOToForm(getFormById(id));
//        Event event=form.getEvent();
//        List<Form> formList=event.getForms();
//        formList.remove(form);
//        event.setForms(formList);
//        eventRepository.save(event);
//        formRepository.deleteById(form.getId());
//        return new MessageResponse("Успешно удалено");
//    }

    public MessageResponse acceptForm(Long id){
        Form form=formFacade.formDTOToForm(getFormById(id));
        form.setAccepted(true);
        formRepository.save(form);
        //Отправка почты
//        try {
//            senderService.sendSimpleEmail(form.getEmail(), "Success", "Ваша анкета одобрена");
//        }catch (MailSendException me){
//
//        }
        return new MessageResponse("Заявка одобрена");
    }
}
