package br.desafiomilionario.agenda.service.impl;

import br.desafiomilionario.agenda.dto.CompromissoDto;
import br.desafiomilionario.agenda.entity.Compromisso;
import br.desafiomilionario.agenda.repository.CompromissoRepository;
import br.desafiomilionario.agenda.service.CompromissoService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CompromissoServiceImpl implements CompromissoService {
    private final CompromissoRepository repository;
    private String msgError = "Esse id de compromisso nao existe!";

    public CompromissoServiceImpl(CompromissoRepository repository) {
        this.repository = repository;
    }
    // @TODO: fazer tratamento de erro

    @Override
    public Long save(CompromissoDto compromissoDto) {
        return repository.save(compromissoDto.toEntity()).getId();
    }

    @Override
    public Compromisso getOne(Long id) {
        if (!repository.existsById(id)){
            throw new NoSuchElementException(msgError);
        }
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void update(Long id, CompromissoDto dto) {
        boolean result = repository.existsById(id);
        if (!result) {
            throw new NoSuchElementException(msgError);
        }

        Compromisso compromisso = new Compromisso();
        compromisso.setId(id);
        compromisso.setTitulo(dto.titulo());
        compromisso.setLocal(dto.local());
        compromisso.setStatus(dto.status());
        compromisso.setDataHora(dto.dataHora());

        repository.save(compromisso);
    }

    @Override
    public void delete(Long id) {
        boolean result = repository.existsById(id);
        if (!result) throw new NoSuchElementException(msgError);
        repository.deleteById(id);
    }
}
