package ru.javaops.topjava2.to;

import lombok.*;
import ru.javaops.topjava2.HasId;
import ru.javaops.topjava2.model.Dish;

import java.util.Set;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo implements HasId {
    Set<Dish> dishes;

    public RestaurantTo(int id, String name, Set<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }
}
