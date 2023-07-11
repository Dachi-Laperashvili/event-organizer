package com.example.eventorganizer.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    @PostMapping(path="/update-task")
    public String updateTask(@RequestParam("taskId") Long taskId,
                           @RequestParam("state") TaskState newState,
                           @RequestParam("money") BigDecimal spentMoney){
         taskService.update(taskId,newState,spentMoney);
         return "redirect:/home/payment-details";
    }
    @GetMapping(value="/update-task")
    public String update(){
        return "UpdateTask";
    }

}
