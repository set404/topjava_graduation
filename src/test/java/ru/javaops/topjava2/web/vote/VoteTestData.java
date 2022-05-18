package ru.javaops.topjava2.web.vote;

import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;
import static ru.javaops.topjava2.web.user.UserTestData.admin;
import static ru.javaops.topjava2.web.user.UserTestData.user;

public class VoteTestData {

    public static final Vote vote1 = new Vote(1, LocalDate.now());
    public static final Vote vote2 = new Vote(2, LocalDate.now().minusDays(1));
    public static final Vote vote3 = new Vote(3, LocalDate.now().minusDays(2));
    public static final Vote vote4 = new Vote(4, LocalDate.now().minusDays(1));

    static {
        vote1.setUser(user);
        vote1.setRestaurant(ollis);
        vote2.setUser(user);
        vote2.setRestaurant(larisa);
        vote3.setUser(user);
        vote3.setRestaurant(berega);
        vote4.setUser(admin);
        vote4.setRestaurant(larisa);
    }

    public static final List<Vote> userVotes = List.of(vote1, vote2, vote3);
    public static final List<Vote> allVotes = List.of(vote1, vote2, vote4, vote3);


    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static VoteTo getNew() {
        return new VoteTo(LocalDate.now(), BEREGA_ID);
    }

    public static VoteTo getUpdated() {
        return new VoteTo(LocalDate.now(), BEREGA_ID);
    }
}
