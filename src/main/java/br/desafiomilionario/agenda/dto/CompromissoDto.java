package br.desafiomilionario.agenda.dto;

import java.util.Date;

public record CompromissoDto(
        Long id,
        String titulo,
        Date dataHora,
        String local,
        Boolean status
) { }
