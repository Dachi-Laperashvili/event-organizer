package com.example.eventorganizer.event;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EventDTO {
    private String name;
    private String description;
}
