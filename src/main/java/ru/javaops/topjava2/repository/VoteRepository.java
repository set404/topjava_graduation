package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.*;
import ru.javaops.topjava2.to.VoteTo;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v FROM Vote v WHERE v.user.id = :user_id AND (:date IS NULL OR v.date = :date)")
    Optional<Vote> getByUserAndDate(int user_id, LocalDate date);
    @Query("SELECT new ru.javaops.topjava2.to.VoteTo(v.id, v.date, v.restaurant.id) FROM Vote v WHERE v.user.id = :user_id " +
            "AND (:startDate IS NULL OR v.date >= :startDate) AND (:endDate IS NULL OR v.date <= :endDate)" +
            "AND (:restaurant_d IS NULL OR v.restaurant.id = :restaurant_id) ORDER BY v.date DESC")
    List<VoteTo> getByFilterForUser(Integer userId, Integer restaurantId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT new ru.javaops.topjava2.model.Rating(v.restaurant.id, COUNT(v)) FROM Vote v " +
            "WHERE (:date IS NULL AND v.date = CURRENT_DATE) OR v.date = :date GROUP BY v.restaurant ORDER BY COUNT(v) DESC")
    List<Rating> getRatingByDate(LocalDate date);
}