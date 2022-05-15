package ru.javaops.topjava2.web.vote;

import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.time.LocalDate;

import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;

public class VoteTestData {

    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static VoteTo getNew() {
        return new VoteTo(LocalDate.now(), BEREGA_ID);
    }

    public static VoteTo getUpdated() {
        return new VoteTo(LocalDate.now(), BEREGA_ID);
    }
}
