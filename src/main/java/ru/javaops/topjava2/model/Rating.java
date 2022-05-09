package ru.javaops.topjava2.model;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Rating {
    int id;
    int rating;

    public Rating(int id, long rating) {
        this.id = id;
        this.rating = (int) rating;
    }
}