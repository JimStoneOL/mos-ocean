package com.joverlost.ejournal.dto;

import lombok.Data;
import java.time.LocalTime;
import java.util.List;

@Data
public class EventTimeDTO{
    private Long id;
    private int amount;
    private LocalTime localTime;
    private Long eventDateId;
    private List<Long> formListId;
    private int restBooking;
}
