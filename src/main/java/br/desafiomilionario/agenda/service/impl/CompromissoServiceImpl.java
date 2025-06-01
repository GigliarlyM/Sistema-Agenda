package br.desafiomilionario.agenda.service.impl;

import br.desafiomilionario.agenda.exception.BusinessException;
import br.desafiomilionario.agenda.model.dto.CompromissoDto;
import br.desafiomilionario.agenda.model.entity.Compromisso;
import br.desafiomilionario.agenda.repository.CompromissoRepository;
import br.desafiomilionario.agenda.service.CompromissoService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CompromissoServiceImpl implements CompromissoService {
    private final CompromissoRepository repository;
    private final String msgError = "Esse id de compromisso nao existe!";

    public CompromissoServiceImpl(CompromissoRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompromissoDto save(CompromissoDto compromissoDto) {
        Compromisso result = repository.save(compromissoDto.toEntity());
        return new CompromissoDto(
                result.getId(),
                result.getTitulo(),
                result.getDataHora(),
                result.getLocal(),
                result.getStatus()
        );
    }

    @Override
    public CompromissoDto getOne(Long id) {
        if (!repository.existsById(id)){
            throw new BusinessException(msgError);
        }
        Compromisso result = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return new CompromissoDto(
                result.getId(),
                result.getTitulo(),
                result.getDataHora(),
                result.getLocal(),
                result.getStatus()
        );
    }

    @Override
    public CompromissoDto update(Long id, CompromissoDto dto) {
        if (!repository.existsById(id)) {
            throw new BusinessException(msgError);
        }
        Compromisso result = repository.save(dto.toEntity());
        return new CompromissoDto(
                result.getId(),
                result.getTitulo(),
                result.getDataHora(),
                result.getLocal(),
                result.getStatus()
        );
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new BusinessException(msgError);
        repository.deleteById(id);
    }
}
