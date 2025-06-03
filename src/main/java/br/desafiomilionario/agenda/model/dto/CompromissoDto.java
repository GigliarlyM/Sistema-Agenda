package br.desafiomilionario.agenda.model.dto;

import br.desafiomilionario.agenda.model.entity.Compromisso;

import java.util.Date;

public record CompromissoDto(
        Long id,
        String titulo,
        Date dataHora,
        String local,
        Boolean status,
        Long agendaId
) {
    public Compromisso toEntity() {
        Compromisso result = new Compromisso();
        result.setId(id);
        result.setTitulo(titulo);
        result.setDataHora(dataHora);
        result.setLocal(local);
        result.setStatus(status);
        return result;
    }
}
