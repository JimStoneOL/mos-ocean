package com.joverlost.ejournal.facade;

import com.joverlost.ejournal.dto.FormDTO;
import com.joverlost.ejournal.entity.Form;
import com.joverlost.ejournal.repository.EventRepository;
import com.joverlost.ejournal.repository.EventTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FormFacade {

    @Autowired
    private EventTimeRepository eventTimeRepository;

    public Form formDTOToForm(FormDTO formDTO){
        Form form=new Form();
        form.setId(formDTO.getId());
        form.setFirstname(formDTO.getFirstname());
        form.setLastname(formDTO.getLastname());
        form.setMiddleName(formDTO.getMiddleName());
        form.setPosition(formDTO.getPosition());
        form.setDivision(formDTO.getDivision());
        form.setEmail(formDTO.getEmail());
        form.setBooking(formDTO.getBooking());
        form.setAccepted(formDTO.isAccepted());
        form.setEventTime(eventTimeRepository.findById(formDTO.getEventTimeId()).get());
        return form;
    }

    public FormDTO formToFormDTO(Form form){
        FormDTO formDTO=new FormDTO();
        formDTO.setId(form.getId());
        formDTO.setFirstname(form.getFirstname());
        formDTO.setLastname(form.getLastname());
        formDTO.setMiddleName(form.getMiddleName());
        formDTO.setPosition(form.getPosition());
        formDTO.setDivision(form.getDivision());
        formDTO.setEmail(form.getEmail());
        formDTO.setBooking(form.getBooking());
        formDTO.setAccepted(form.isAccepted());
        formDTO.setEventTimeId(form.getEventTime().getId());
        return formDTO;
    }

    public List<FormDTO> formListToFormDTOList(List<Form> formList){
        List<FormDTO> formDTOList=new ArrayList<>();
        for(int i=0;i<formList.size();i++){
            Form form=formList.get(i);
            FormDTO formDTO=formToFormDTO(form);
            formDTOList.add(formDTO);
        }
        return formDTOList;
    }
}