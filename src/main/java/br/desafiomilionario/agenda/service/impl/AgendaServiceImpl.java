package br.desafiomilionario.agenda.service.impl;

import br.desafiomilionario.agenda.exception.BusinessException;
import br.desafiomilionario.agenda.model.dto.AgendaDto;
import br.desafiomilionario.agenda.model.entity.Agenda;
import br.desafiomilionario.agenda.model.entity.Compromisso;
import br.desafiomilionario.agenda.model.entity.Relatorio;
import br.desafiomilionario.agenda.model.entity.Usuario;
import br.desafiomilionario.agenda.repository.AgendaRepository;
import br.desafiomilionario.agenda.service.AgendaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaServiceImpl implements AgendaService {
    private final AgendaRepository repository;

    public AgendaServiceImpl(AgendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public AgendaDto getOneAgenda(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Id de agenda nao existe");
        }
        Agenda agenda = repository.findById(id).orElseThrow();
        List<Long> longCompromissos;
        List<Long> longRelatorios;

        longCompromissos = agenda.getCompromissos()
                .stream().map(
                        Compromisso::getId
                ).toList();

        longRelatorios = agenda.getRelatorios()
                .stream().map(
                        Relatorio::getId
                ).toList();

        return new AgendaDto(
                agenda.getId(),
                longCompromissos,
                longRelatorios,
                agenda.getUsuario().getEmail()
        );
    }

    @Override
    public AgendaDto createAgenda(AgendaDto dto, Usuario usuario) {
        Agenda agenda = new Agenda();
        agenda.setUsuario(usuario);
        Agenda result = repository.save(agenda);
        List<Long> longCompromissos = null;
        List<Long> longRelatorios = null;

        if (result.getCompromissos() != null) {
            longCompromissos = result.getCompromissos()
                    .stream().map(
                            Compromisso::getId
                    ).toList();
        }
        if (result.getRelatorios() != null) {
            longRelatorios = result.getRelatorios()
                    .stream().map(
                            Relatorio::getId
                    ).toList();
        }

        return new AgendaDto(
                result.getId(),
                longCompromissos,
                longRelatorios,
                result.getUsuario().getEmail()
        );
    }

    @Override
    public void deleteAgenda(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Id de agenda nao existe!!");
        }
        repository.deleteById(id);
    }

    @Override
    public Agenda findOneAgenda(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Id de agenda nao existe!!");
        }
        return repository.findById(id).orElseThrow();
    }
}
