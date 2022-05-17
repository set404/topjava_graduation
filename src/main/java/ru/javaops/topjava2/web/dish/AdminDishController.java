package ru.javaops.topjava2.web.dish;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.DishTo;
import ru.javaops.topjava2.util.DishUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.topjava2.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.topjava2.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
//@CacheConfig(cacheNames = "restaurants")
public class AdminDishController {
    public static final String REST_URL = "/api/admin/dishes/";
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;


    @GetMapping
    public List<Dish> getAllByRestaurant(@RequestParam int restaurantId) {
        log.info("get All dishes for restaurant {}", restaurantId);
        return dishRepository.getAllByRestaurant(restaurantId);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(dishRepository.findById(id));
    }


    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    //@CacheEvict(allEntries = true)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo,
                                                   @RequestParam int restaurantId) {
        log.info("add dish {} to restaurant {}", dishTo, restaurantId);
        checkNew(dishTo);
        Dish dish = DishUtil.createFromTo(dishTo);
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        dish.setDate(LocalDate.now());
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody DishTo to,
                       @PathVariable int id,
                       @RequestParam int restaurantId) {
        log.info("update dish id = {} for restaurant = {}", id, restaurantId);
        assureIdConsistent(to, id);
        Dish dish = DishUtil.createFromTo(to);
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        dish.setDate(LocalDate.now());
        dishRepository.save(dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@CacheEvict(allEntries = true)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        dishRepository.delete(id);
    }
}
