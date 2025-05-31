package br.desafiomilionario.agenda.model.dto;

public record UsuarioDto(
        String email,
        String nome,
        String telefone,
        Long agendaId
) {}
