package ru.javaops.topjava2.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaops.topjava2.repository.RestaurantRepository;

public abstract class BaseRestaurantController {
    protected static final Logger log = LoggerFactory.getLogger(BaseRestaurantController.class);

    @Autowired
    protected RestaurantRepository repository;
}
