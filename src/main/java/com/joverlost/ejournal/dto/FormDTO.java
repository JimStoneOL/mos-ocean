package com.joverlost.ejournal.dto;

import com.joverlost.ejournal.entity.Event;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FormDTO {
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String middleName;
    @NotEmpty
    private String position;
    @NotEmpty
    private String division;
    @NotEmpty
    private String email;
    @NotNull
    @Max(4)
    private int booking;
    private boolean accepted;
    @NotNull
    private Long eventTimeId;
}
