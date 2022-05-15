package ru.javaops.topjava2.web.restaurant;

import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.RestaurantTo;
import ru.javaops.topjava2.web.MatcherFactory;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> REST_MATCHER
            = MatcherFactory.usingEqualsComparator(Restaurant.class);
    public static final MatcherFactory.Matcher<RestaurantTo> REST_TO_MATCHER
            = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int OLLIS_ID = 1;
    public static final int BEREGA_ID = 2;
    public static final int LARISA_ID = 3;

    public static final int NOT_FOUND = 100;

    public static final Restaurant ollis = new Restaurant(OLLIS_ID, "Ollis");
    public static final Restaurant berega = new Restaurant(BEREGA_ID, "2berega");
    public static final Restaurant larisa = new Restaurant(LARISA_ID, "larisuvannuhochu");


    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(BEREGA_ID, "Updated 2berega");
    }
}
