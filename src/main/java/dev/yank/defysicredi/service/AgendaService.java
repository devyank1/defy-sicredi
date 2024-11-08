package dev.yank.defysicredi.service;

import dev.yank.defysicredi.model.Agenda;
import dev.yank.defysicredi.repository.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Agenda> listAllAgenda() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> findAgendaById(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda saveAgenda(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Agenda newAgenda(Long id, Agenda agendaActualized) {
        return agendaRepository.findById(id).map(agenda -> {
            agenda.setTitle(agendaActualized.getTitle());
            agenda.setDescription(agendaActualized.getDescription());
            agenda.setVotes(agendaActualized.getVotes());
            agenda.setOpenedSession(agendaActualized.isOpenedSession());
            return agendaRepository.save(agenda);
        }).orElseThrow(() -> new RuntimeException("Agenda not found!"));
    }

    public void deleteAgenda(Long id) {
        agendaRepository.deleteById(id);
    }
}
