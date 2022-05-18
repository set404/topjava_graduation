package ru.javaops.topjava2.web.dish;

import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.DishTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.time.LocalDate;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER
            = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final MatcherFactory.Matcher<DishTo> DISH_TO_MATCHER
            = MatcherFactory.usingEqualsComparator(DishTo.class);

    public static final int DISH_ID = 1;
    public static final int TESTDISH_ID = 4;

    public static final Dish ollis_dish1 = new Dish(DISH_ID, "Borsh", 200, LocalDate.now());
    public static final Dish ollis_dish2 = new Dish(DISH_ID + 1, "Pizza", 700, LocalDate.now());

    public static final Dish berega_dish1 = new Dish(DISH_ID + 2, "Sushi", 500, LocalDate.now());
    public static final Dish berega_dish2 = new Dish(DISH_ID + 3, "Burger", 600, LocalDate.now());

    public static final Dish larisa_dish1 = new Dish(DISH_ID + 4, "Shawerma", 150, LocalDate.now());
    public static final Dish larisa_dish2 = new Dish(DISH_ID + 5, "Potatoes Free", 100, LocalDate.now());

    public static Dish getNew() {
        return new Dish(null, "New Dish", 200, LocalDate.now());
    }
}
