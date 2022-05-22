package ru.javaops.topjava2.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.RestaurantTo;
import ru.javaops.topjava2.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class UserRestaurantController {

    static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository restaurantRepository;

    @GetMapping("/withMenu")
    @Cacheable("restaurants")
    public List<RestaurantTo> getAllWithDishesToday() {
        log.info("get all restaurants with dishes today");
        return RestaurantUtil.getTos(restaurantRepository.getAllByDateWithDishes(LocalDate.now()));
    }

    @GetMapping("/{id}/withMenu")
    public RestaurantTo getByIdWithDishesToday(@PathVariable int id) {
        log.info("get dishes from restaurant {} today", id);
        RestaurantTo restaurant = restaurantRepository.getByIdAndDateWithDishes(id, LocalDate.now()).map(RestaurantUtil::createTo).orElse(null);
        if (restaurant == null) {
            throw new IllegalRequestDataException("Restaurant id=" + id + " not found");
        }
        return restaurant;
    }
}
