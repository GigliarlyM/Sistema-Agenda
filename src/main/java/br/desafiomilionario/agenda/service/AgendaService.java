package br.desafiomilionario.agenda.service;

import br.desafiomilionario.agenda.model.dto.AgendaDto;
import br.desafiomilionario.agenda.model.entity.Agenda;
import br.desafiomilionario.agenda.model.entity.Usuario;

public interface AgendaService {
    AgendaDto getOneAgenda(Long id);

    AgendaDto createAgenda(AgendaDto dto, Usuario usuario);

    void deleteAgenda(Long id);

    Agenda findOneAgenda(Long id);
}
