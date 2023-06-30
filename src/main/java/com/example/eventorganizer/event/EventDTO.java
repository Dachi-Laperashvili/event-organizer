package com.example.eventorganizer.event;

import com.example.eventorganizer.user.User;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EventDTO {
    private String name;
    private String description;
}
