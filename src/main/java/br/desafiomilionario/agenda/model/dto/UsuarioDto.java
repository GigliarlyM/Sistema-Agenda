package br.desafiomilionario.agenda.model.dto;

import br.desafiomilionario.agenda.model.validation.Email;
import br.desafiomilionario.agenda.model.validation.Telefone;

import javax.naming.directory.NoSuchAttributeException;

public class UsuarioDto {
    Email email;
    String nome;
    Telefone telefone;
    Long agendaId;

    public UsuarioDto(
            String email,
            String nome,
            String telefone,
            Long agendaId
    ) throws NoSuchAttributeException {
        this.email = new Email(email);
        this.telefone = new Telefone(telefone);
        this.nome = nome;
        this.agendaId = agendaId;
    }

    public String email() {
        return this.email.value();
    }

    public String telefone() {
        return this.telefone.value();
    }

    public String nome() {
        return this.nome;
    }

    public Long agendaId() {
        return this.agendaId;
    }
}
