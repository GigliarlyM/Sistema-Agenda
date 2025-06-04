package br.desafiomilionario.agenda.service;

import br.desafiomilionario.agenda.model.dto.CompromissoDto;
import br.desafiomilionario.agenda.model.entity.Agenda;
import br.desafiomilionario.agenda.model.entity.Compromisso;

public interface CompromissoService {
    CompromissoDto save(CompromissoDto compromissoDto, Agenda agenda);
    CompromissoDto getOne(Long id);
    CompromissoDto update(Long id, CompromissoDto compromissoDto, Agenda agenda);
    void delete(Long id);
    Compromisso findOneCompromisso(Long id);
}
