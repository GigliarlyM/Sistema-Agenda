package br.desafiomilionario.agenda.model.dto;

import br.desafiomilionario.agenda.model.validation.Email;

import java.util.List;

// Usuario não vai ter metodos de alteracao de dados
// para esse record
public record AgendaDto(
        Long id,
        List<Integer> compromissos,
        List<Integer> relatorios,
        Email usuarioEmail
) {
}
