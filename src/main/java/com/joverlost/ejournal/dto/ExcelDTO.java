package com.joverlost.ejournal.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ExcelDTO implements Comparable<ExcelDTO>{
    private String название_мероприятия;
    private LocalDate дата;
    private LocalTime время;
    private int всего_билетов;
    private int забронировано;
    private int всего_осталось_билетов;
    private String фио;
    private String должность;
    private String подразделение;
    private String email;
    private boolean одобрен;

    @Override
    public int compareTo(ExcelDTO o) {
        return getДата().compareTo(o.getДата());
    }
}
