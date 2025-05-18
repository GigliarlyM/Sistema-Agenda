package br.desafiomilionario.agenda.service;

import br.desafiomilionario.agenda.dto.CompromissoDto;
import br.desafiomilionario.agenda.entity.Compromisso;

public interface CompromissoService {
    Long save(CompromissoDto compromissoDto);
    Compromisso getOne(Long id);
    void update(Long id, CompromissoDto compromissoDto);
    void delete(Long id);
}
