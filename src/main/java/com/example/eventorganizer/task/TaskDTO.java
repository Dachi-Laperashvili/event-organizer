package com.example.eventorganizer.task;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TaskDTO {
    private String name;
    private TaskState state;
    private BigDecimal spentMoney;

}
