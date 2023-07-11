package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.user.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContributionService {
    public Map<User, BigDecimal> calculateContributions(Event event, User spender, BigDecimal spentMoney){
        List<User> participants = event.getUsers().stream().filter(user -> !user.equals(spender)).toList();

        int numParticipants = participants.size() + 1;
        if( numParticipants == 1){
            return Map.of();
        }

        BigDecimal contributionPerPerson = spentMoney.divide(BigDecimal.valueOf(numParticipants),2, RoundingMode.HALF_UP);
        return participants.stream().collect(Collectors.toMap(user -> user,user -> contributionPerPerson));
    }
}
