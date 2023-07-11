package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.user.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ContributionServiceTest {
    @Test
    public void testCalculateContributions(){
        Event event = new Event();
        User spender = new User();
        User user1 = new User();
        User user2 = new User();
        event.addUser(user1);
        event.addUser(user2);

        BigDecimal spentMoney = new BigDecimal(90);

        ContributionService contributionService =  new ContributionService();

        Map<User,BigDecimal> contributions = contributionService.calculateContributions(event,spender,spentMoney);

        BigDecimal contributionPerPerson = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_UP);
        assertEquals(contributionPerPerson,contributions.get(user1));
        assertEquals(contributionPerPerson,contributions.get(user2));
        assertNull(contributions.get(spender));
    }
}