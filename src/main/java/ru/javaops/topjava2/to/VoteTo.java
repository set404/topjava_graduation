package ru.javaops.topjava2.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(value = "id")
public class VoteTo extends BaseTo {
    LocalDate date;
    int restaurantId;

    public VoteTo(Integer id, LocalDate date, int restaurantId) {
        super(id);
        this.date = date;
        this.restaurantId = restaurantId;
    }
}
