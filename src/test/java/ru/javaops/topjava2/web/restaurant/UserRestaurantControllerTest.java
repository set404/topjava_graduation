package ru.javaops.topjava2.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.util.RestaurantUtil;
import ru.javaops.topjava2.web.AbstractControllerTest;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.dish.DishTestData.*;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;
import static ru.javaops.topjava2.web.user.UserTestData.USER_MAIL;

@WithUserDetails(value = USER_MAIL)
class UserRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserRestaurantController.REST_URL + '/';

    @Test
    void getByIdWithMenuToday() throws Exception {
        berega.setDishes(List.of(berega_dish2, berega_dish1));

        perform(MockMvcRequestBuilders.get(REST_URL + BEREGA_ID + "/menu"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(REST_TO_MATCHER.contentJson(RestaurantUtil.createTo(berega)));
    }

    @Test
    void getAllWithMenuToday() throws Exception {
        berega.setDishes(List.of(berega_dish2, berega_dish1));
        ollis.setDishes(List.of(ollis_dish2, ollis_dish1));
        larisa.setDishes(List.of(larisa_dish1, larisa_dish2));
        perform(MockMvcRequestBuilders.get(REST_URL + "menu"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(REST_TO_MATCHER.contentJson(RestaurantUtil.getTos(List.of(berega, ollis, larisa))));
    }
}
