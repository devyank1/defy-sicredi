package dev.yank.defysicredi.service;

import dev.yank.defysicredi.model.Agenda;
import dev.yank.defysicredi.model.Vote;
import dev.yank.defysicredi.repository.AgendaRepository;
import dev.yank.defysicredi.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final AgendaRepository agendaRepository;

    public VoteService(VoteRepository voteRepository, AgendaRepository agendaRepository) {
        this.voteRepository = voteRepository;
        this.agendaRepository = agendaRepository;
    }

    public List<Vote> listAllVotes() {
        return voteRepository.findAll();
    }

    public Optional<Vote> findVoteById(Long id){
        return voteRepository.findById(id);
    }

    @Transactional
    public Vote saveVote(Vote vote) {
        String voteOption = vote.getVoteOption();
        if (!voteOption.equalsIgnoreCase("Sim") &&
                !voteOption.equalsIgnoreCase("Não") &&
                !voteOption.equalsIgnoreCase("Yes") &&
                !voteOption.equalsIgnoreCase("No")) {
            throw new IllegalArgumentException("Opções de voto válidas são: 'Sim', 'Não', 'Yes' ou 'No'.");
        }

        boolean hasVoted = voteRepository.existsByAssociated_IdAndAgenda_Id(
                vote.getAssociated().getId(),
                vote.getAgenda().getId()
        );

        if (hasVoted) {
            throw new RuntimeException("Associated has already voted in this agenda.");
        }

        Vote savedVote = voteRepository.save(vote);

        Agenda agenda = savedVote.getAgenda();
        agenda.setTotalVotes(agenda.getTotalVotes() + 1);

        agendaRepository.save(agenda);

        return savedVote;
    }

    public Vote newVote(Long id, Vote voteActualized) {
        return voteRepository.findById(id).map(vote -> {
            vote.setAssociated(voteActualized.getAssociated());
            vote.setAgenda(voteActualized.getAgenda());
            return voteRepository.save(vote);
        }).orElseThrow(() -> new RuntimeException("Vote not found!"));

    }

    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }
}
