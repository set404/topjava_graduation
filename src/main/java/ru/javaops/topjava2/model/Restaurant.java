package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant")
public class Restaurant extends NamedEntity {
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @OrderBy("name DESC")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dish> dishes;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @OrderBy("date DESC")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
