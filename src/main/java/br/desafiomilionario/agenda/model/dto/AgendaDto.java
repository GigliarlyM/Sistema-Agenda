package br.desafiomilionario.agenda.model.dto;

import java.util.List;

// Usuario não vai ter metodos de alteracao de dados
// para esse record
public record AgendaDto(
        Long id,
        List<Long> compromissos,
        List<Long> relatorios,
        String usuarioEmail
) {
}
