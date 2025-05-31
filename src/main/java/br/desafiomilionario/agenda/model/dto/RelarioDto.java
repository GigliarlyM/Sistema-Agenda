package br.desafiomilionario.agenda.model.dto;

import java.util.Date;

// O usuario poderá, apenas, visualizar o relatorio
public record RelarioDto(
        Long id,
        Date dataInicio,
        Date dataFim
) {}
