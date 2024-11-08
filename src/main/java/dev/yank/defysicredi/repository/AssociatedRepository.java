package dev.yank.defysicredi.repository;

import dev.yank.defysicredi.model.Associated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociatedRepository extends JpaRepository<Associated, Long> {
}
