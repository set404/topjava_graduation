package ru.javaops.topjava2.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.util.VoteUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminVoteController {

    static final String REST_URL = "/api/admin/votes";

    private final VoteRepository voteRepository;

    @GetMapping
    public List<VoteTo> getAll() {
        log.info("get all votes");
        return VoteUtil.getTos(voteRepository.getAll());
    }

    @GetMapping("/by-date")
    public List<VoteTo> get(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate today = LocalDate.now();
        if (date == null) {
            log.info("get all votes by current date");
        }
        else {
            log.info("get all votes by {}", date);
        }
        return VoteUtil.getTos(voteRepository.getAllByDate(today));

    }
}