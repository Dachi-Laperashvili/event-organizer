package com.example.eventorganizer.task;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TaskDTO {
    private String name;
    public TaskState state;
}
