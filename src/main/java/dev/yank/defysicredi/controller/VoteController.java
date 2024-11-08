package dev.yank.defysicredi.controller;

import dev.yank.defysicredi.model.Vote;
import dev.yank.defysicredi.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public List<Vote> listAllVotes() {
        return voteService.listAllVotes();
    }

    @GetMapping("/{id}")
    public Optional<Vote> findVote(@PathVariable Long id) {
        return voteService.findVoteById(id);
    }

    @PostMapping
    public ResponseEntity<?> saveVote(@RequestBody Vote vote) {
        try {
            Vote savedVote = voteService.saveVote(vote);
            return ResponseEntity.ok(savedVote);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vote> newVote(@RequestBody Vote vote, @PathVariable Long id) {
        return ResponseEntity.ok(voteService.newVote(id, vote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
        return ResponseEntity.noContent().build();
    }
}
