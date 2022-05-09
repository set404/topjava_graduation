package ru.javaops.topjava2.to;

import lombok.*;
import ru.javaops.topjava2.HasId;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo implements HasId {
    List<DishTo> dishes;

    public RestaurantTo(int id, String name, List<DishTo> dishes) {
        super(id, name);
        this.dishes = dishes;
    }
}
