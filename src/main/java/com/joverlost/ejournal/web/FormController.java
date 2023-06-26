package com.joverlost.ejournal.web;

import com.joverlost.ejournal.dto.FormDTO;
import com.joverlost.ejournal.service.FormService;
import com.joverlost.ejournal.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/form")
@CrossOrigin
public class FormController {

    @Autowired
    private FormService formService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/create")
    public ResponseEntity<Object> createForm(@Valid @RequestBody FormDTO formDTO, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        return new ResponseEntity<>(formService.createForm(formDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllForms(){
        return new ResponseEntity<>(formService.getAllForms(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getFormById(@PathVariable Long id){
        return new ResponseEntity<>(formService.getFormById(id), HttpStatus.OK);
    }

//    @PostMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteForm(@PathVariable Long id){
//        return new ResponseEntity<>(formService.deleteForm(id), HttpStatus.OK);
//    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<Object> acceptForm(@PathVariable Long id){
        return new ResponseEntity<>(formService.acceptForm(id),HttpStatus.OK);
    }

    @GetMapping("/get/all/by/event/{id}")
    public ResponseEntity<Object> getAllByEventId(@PathVariable Long id){
        return new ResponseEntity<>(formService.getAllFormsByEventId(id), HttpStatus.OK);
    }
}
