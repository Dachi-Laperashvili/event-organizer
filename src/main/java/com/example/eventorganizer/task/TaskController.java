package com.example.eventorganizer.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    @PostMapping("/updateTask")
    public String updateTask(@RequestParam("taskId") Long taskId,
                           @RequestParam("newState") TaskState newState,
                           @RequestParam("money") BigDecimal spentMoney){
        taskService.update(taskId,newState,spentMoney);
        return "redirect:home/paymentDetails";
    }
}
