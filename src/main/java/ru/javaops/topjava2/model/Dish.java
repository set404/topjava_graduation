package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javaops.topjava2.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "date", "name", "price", "restaurant_id"}, name = "dish_restaurant_idx")})
public class Dish extends NamedEntity {
    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @Column(name = "price", nullable = false, columnDefinition = "int default 0")
    @NotNull
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(LocalDate date, String name, Integer price) {
        this(null, date, name, price);
    }

    public Dish(Integer id, LocalDate date, String name, Integer price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
