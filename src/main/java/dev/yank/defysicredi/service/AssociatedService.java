package dev.yank.defysicredi.service;

import dev.yank.defysicredi.model.Associated;
import dev.yank.defysicredi.repository.AssociatedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociatedService {

    private final AssociatedRepository associatedRepository;

    public AssociatedService(AssociatedRepository associatedRepository) {
        this.associatedRepository = associatedRepository;
    }

    public List<Associated> listAllAssociated() {
        return associatedRepository.findAll();
    }

    public Optional<Associated> findAssociatedById(Long id) {
        return associatedRepository.findById(id);
    }

    public Associated saveUser(Associated associated) {
        return associatedRepository.save(associated);
    }

    public Associated newUser(Long id, Associated associatedActualized) {
        return associatedRepository.findById(id).map(associated -> {
            associated.setName(associatedActualized.getName());
            associated.setCpf(associatedActualized.getCpf());
            return associatedRepository.save(associated);
        }).orElseThrow(() -> new RuntimeException("Associated not found!"));
    }

    public void deleteAssociated(Long id) {
        associatedRepository.deleteById(id);
    }
}
