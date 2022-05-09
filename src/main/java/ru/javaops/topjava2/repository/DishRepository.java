package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Dish;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Query("SELECT d FROM Dish d WHERE :restaurant_id IS NULL OR d.restaurant.id = :restaurant_id")
    List<Dish> getByRestaurantId(Integer restaurant_id);

    @Query("SELECT d FROM Dish d WHERE d.id = :id AND d.restaurant.id = :restaurant_id")
    Optional<Dish> get(int id, int restaurant_id);
}
