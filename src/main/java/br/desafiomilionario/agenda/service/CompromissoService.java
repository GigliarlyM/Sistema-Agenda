package br.desafiomilionario.agenda.service;

import br.desafiomilionario.agenda.model.dto.CompromissoDto;
import br.desafiomilionario.agenda.model.entity.Compromisso;

public interface CompromissoService {
    CompromissoDto save(CompromissoDto compromissoDto);
    CompromissoDto getOne(Long id);
    CompromissoDto update(Long id, CompromissoDto compromissoDto);
    void delete(Long id);
    Compromisso findOneCompromisso(Long id);
}
