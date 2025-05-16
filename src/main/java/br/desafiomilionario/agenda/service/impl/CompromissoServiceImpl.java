package br.desafiomilionario.agenda.service.impl;

import br.desafiomilionario.agenda.dto.CompromissoDto;
import br.desafiomilionario.agenda.entity.Compromisso;
import br.desafiomilionario.agenda.repository.CompromissoRepository;
import br.desafiomilionario.agenda.service.CompromissoService;
import org.springframework.stereotype.Service;

@Service
public class CompromissoServiceImpl implements CompromissoService {
    private final CompromissoRepository repository;

    public CompromissoServiceImpl(CompromissoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(CompromissoDto compromissoDto) {

    }

    @Override
    public Compromisso getOne(Long id) {
        return null;
    }

    @Override
    public void update(Long id, CompromissoDto compromissoDto) {

    }

    @Override
    public void delete(Long id) {

    }
}
