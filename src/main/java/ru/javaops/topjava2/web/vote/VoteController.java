package ru.javaops.topjava2.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.UserRepository;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.util.VoteUtil;
import ru.javaops.topjava2.web.AuthUser;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {

    static final String REST_URL = "/api/vote";

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get all votes for user {}", authUser.id());
        return VoteUtil.getTos(voteRepository.getAll(authUser.id()));
    }

    @GetMapping("/today")
    public Optional<VoteTo> get(@AuthenticationPrincipal AuthUser authUser,
                                @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int userId = authUser.id();
        LocalDate voteDate = (date == null) ? LocalDate.now() : date;
        if (date == null) {
            log.info("get votes by current date");
        } else {
            log.info("get votes by date {}", date);
        }
        return voteRepository.getByDate(userId, voteDate).map(VoteUtil::createTo);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<VoteTo> createOrUpdate(@AuthenticationPrincipal AuthUser authUser,
                                                 @RequestParam int restaurantId) {
        int userId = authUser.id();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalRequestDataException("Restaurant id=" + restaurantId + " not found")
        );
        Vote vote = voteRepository.getByDate(userId, LocalDate.now()).orElse(
                new Vote(LocalDate.now(), userRepository.getById(userId), restaurant)
        );
        if (vote.isNew()) {
            log.info("vote saved for restaurant {}", restaurantId);
            Vote created = voteRepository.save(vote);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL).buildAndExpand().toUri();
            return ResponseEntity.created(uriOfNewResource).body(VoteUtil.createTo(created));
        }
        if (vote.getRestaurant().id() == restaurantId) {
            log.info("trying to vote for restaurant {} again", restaurantId);
        } else {
            log.info("user {} changed vote for restaurant {}", userId, restaurantId);
            vote.setRestaurant(restaurant);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}