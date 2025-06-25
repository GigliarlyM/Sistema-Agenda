package br.desafiomilionario.agenda.model.dto;

import java.util.List;

public record AgendaWithComprDto(
        Long id,
        List<CompromissoDto> compromissos,
        String usuarioEmail
) {}
