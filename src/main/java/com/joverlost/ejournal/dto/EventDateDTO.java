package com.joverlost.ejournal.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EventDateDTO{
    private Long id;
    private LocalDate localDate;
    private Long eventId;
    private List<Long> eventTimeListId;
}
