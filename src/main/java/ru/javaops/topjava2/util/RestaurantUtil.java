package ru.javaops.topjava2.util;

import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtil {
    private RestaurantUtil() {
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.id(), restaurant.getName(), DishUtil.getTos(restaurant.getDishes()));
    }

    public static List<RestaurantTo> getTos(List<Restaurant> restaurantList) {
        return restaurantList.stream()
                .map(RestaurantUtil::createTo)
                .collect(Collectors.toList());
    }
}
