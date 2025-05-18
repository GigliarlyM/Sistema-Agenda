package br.desafiomilionario.agenda.dto;

import br.desafiomilionario.agenda.entity.Compromisso;

import java.util.Date;

public record CompromissoDto(
        Long id,
        String titulo,
        Date dataHora,
        String local,
        Boolean status
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
