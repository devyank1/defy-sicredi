package dev.yank.defysicredi.controller;

import dev.yank.defysicredi.model.Agenda;
import dev.yank.defysicredi.service.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public List<Agenda> listAllAgendas() {
        return agendaService.listAllAgenda();
    }

    @GetMapping("/{id}")
    public Optional<Agenda> findById(@PathVariable Long id) {
        return agendaService.findAgendaById(id);
    }

    @PostMapping
    public Agenda saveAgenda(@RequestBody Agenda agenda) {
        return agendaService.saveAgenda(agenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> newAgenda(@RequestBody Agenda agenda, @PathVariable Long id) {
        return ResponseEntity.ok(agendaService.newAgenda(id, agenda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenda(@PathVariable Long id) {
        agendaService.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }
}
