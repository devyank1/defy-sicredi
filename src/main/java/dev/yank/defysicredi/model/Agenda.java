package dev.yank.defysicredi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vote> votes;

    private boolean openedSession;

    private Integer totalVotes = 0;

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Agenda(Long id, String title, String description, List<Vote> votes, boolean openedSession) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.votes = votes;
        this.openedSession = openedSession;
    }

    public Agenda() {}

    public boolean isOpenedSession() {
        return openedSession;
    }

    public void setOpenedSession(boolean openedSession) {
        this.openedSession = openedSession;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
