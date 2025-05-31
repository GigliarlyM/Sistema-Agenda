package br.desafiomilionario.agenda.model.dto;

import br.desafiomilionario.agenda.model.validation.Email;
import br.desafiomilionario.agenda.model.validation.Telefone;

public record UsuarioDto(
        Email email,
        String nome,
        Telefone telefone,
        Long agendaId
) {}
