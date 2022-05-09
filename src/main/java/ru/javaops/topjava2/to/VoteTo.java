package ru.javaops.topjava2.to;

import ru.javaops.topjava2.HasId;

public class VoteTo extends BaseTo implements HasId {
    private Integer id;
    private String name;
    private Long votes;

    public VoteTo() {
    }

    public VoteTo(Integer id, String name, Long votes) {
        this.id = id;
        this.name = name;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "ResultTo{" +
                "restaurantId=" + id +
                ", name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }
}
