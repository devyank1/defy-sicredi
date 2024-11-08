package dev.yank.defysicredi.controller;

import dev.yank.defysicredi.model.Associated;
import dev.yank.defysicredi.service.AssociatedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/associated")
public class AssociatedController {

    private final AssociatedService associatedService;

    public AssociatedController(AssociatedService associatedService) {
        this.associatedService = associatedService;
    }

    @GetMapping("/{id}")
    public Optional<Associated> findById(@PathVariable Long id){
        return associatedService.findAssociatedById(id);
    }

    @GetMapping
    public List<Associated> listAllClients() {
        return associatedService.listAllAssociated();
    }

    @PostMapping
    public Associated saveAssociated(@RequestBody Associated associated) {
        return associatedService.saveUser(associated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Associated> newAssociated(@PathVariable Long id, @RequestBody Associated associated) {
        return ResponseEntity.ok(associatedService.newUser(id, associated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociated(@PathVariable Long id) {
        associatedService.deleteAssociated(id);
        return ResponseEntity.noContent().build();
    }
}
