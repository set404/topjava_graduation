package ru.javaops.topjava2.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.util.DateTimeUtil;
import ru.javaops.topjava2.util.VoteUtil;
import ru.javaops.topjava2.web.AbstractControllerTest;
import ru.javaops.topjava2.web.user.UserTestData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.BEREGA_ID;
import static ru.javaops.topjava2.web.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.topjava2.web.user.UserTestData.USER_MAIL;
import static ru.javaops.topjava2.web.vote.VoteTestData.*;

class UserVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserVoteController.REST_URL + '/';

    @Autowired
    private VoteRepository repository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.getTos(userVotes)));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/by-date"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.getTos(List.of(vote1))));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        VoteTo newVote = VoteTestData.getNew();
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", String.valueOf(BEREGA_ID)))
                .andDo(print());
        VoteTo created = repository.getByDateForUser(UserTestData.ADMIN_ID, LocalDate.now()).map(VoteUtil::createTo).orElse(null);
        VOTE_TO_MATCHER.assertMatch(created, newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        VoteTo updated = VoteTestData.getUpdated();
        DateTimeUtil.setExpiredTime(LocalTime.MAX);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", String.valueOf(BEREGA_ID)))
                .andDo(print());
        VOTE_TO_MATCHER.assertMatch(repository.getByDateForUser(UserTestData.USER_ID, LocalDate.now()).map(VoteUtil::createTo).orElse(null), updated);
    }

}
