package dev.yank.defysicredi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_associated", "id_agenda"})})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "id_associated")
    private Associated associated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_agenda")
    private Agenda agenda;

    private String voteOption;

    @Column(nullable = false)
    public String getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(String voteOption) {
        this.voteOption = voteOption;
    }

    public Vote(Long voteId, Associated associated, Agenda agenda) {
        this.voteId = voteId;
        this.associated = associated;
        this.agenda = agenda;
    }

    public Vote() {}

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Associated getAssociated() {
        return associated;
    }

    public void setAssociated(Associated associated) {
        this.associated = associated;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

}
