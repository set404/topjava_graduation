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
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id"}, name = "vote_date_user_idx")})
public class Vote extends BaseEntity {
    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDate date, User user, Restaurant restaurant) {
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
