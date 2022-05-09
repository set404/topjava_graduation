package ru.javaops.topjava2.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.DishRepository;
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

    private final RestaurantRepository repository;

    @GetMapping("/with-menu")
    @Cacheable("restaurants")
    public List<RestaurantTo> getAllWithMenuToday() {
        log.info("get all restaurants with menu today");
        return RestaurantUtil.getTos(repository.getAllByDateWithMenu(LocalDate.now()));
    }

    @GetMapping("/{id}/with-menu")
    public RestaurantTo getByIdWithMenuToday(@PathVariable int id) {
        log.info("get restaurant {} with menu today", id);
        return repository.getByIdAndDateWithMenu(id, LocalDate.now()).map(RestaurantUtil::createTo).orElseThrow(
                () -> new IllegalRequestDataException("Restaurant with id=" + id + " not found"));
    }
}
