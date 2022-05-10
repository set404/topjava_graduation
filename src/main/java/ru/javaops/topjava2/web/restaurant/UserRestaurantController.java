package ru.javaops.topjava2.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.RestaurantTo;
import ru.javaops.topjava2.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class UserRestaurantController {

    static final String REST_URL = "/api/restaurant";

    private final RestaurantRepository repository;

    @GetMapping("/menu")
    public List<RestaurantTo> getAllWithDishesToday() {
        log.info("get all restaurants with dishes today");
        return RestaurantUtil.getTos(repository.getAllByDateWithDishes(LocalDate.now()));
    }

    @GetMapping("/{id}/menu")
    public Optional<RestaurantTo> getByIdWithDishesToday(@PathVariable int id) {
        log.info("get dishes from restaurant {} today", id);
        return repository.getByIdAndDateWithDishes(id, LocalDate.now()).map(RestaurantUtil::createTo);
    }
}
