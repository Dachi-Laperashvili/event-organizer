package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.user.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ContributionService {
    public Map<User, BigDecimal> calculateContributions(Event event, User spender, BigDecimal spentMoney){
        List<User> participants = new ArrayList<>(event.getUsers());
        participants.remove(spender);

        int numParticipants = participants.size() + 1;
        if( numParticipants == 1){
            return Collections.emptyMap();
        }

        BigDecimal contributionPerPerson = spentMoney.divide(BigDecimal.valueOf(numParticipants),2, RoundingMode.HALF_UP);

        Map<User, BigDecimal> contributions = new HashMap<>();

        for(User participant: participants){
            contributions.put(participant,contributionPerPerson);
        }
        return contributions;
    }
}
