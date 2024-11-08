package dev.yank.defysicredi.repository;

import dev.yank.defysicredi.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByAssociated_IdAndAgenda_Id(Long associatedId, Long agendaId);
}
