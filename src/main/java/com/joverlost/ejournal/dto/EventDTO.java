package com.joverlost.ejournal.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private List<Long> eventDateListId;
}
