package br.desafiomilionario.agenda.service;

import br.desafiomilionario.agenda.model.dto.UsuarioDto;
import br.desafiomilionario.agenda.model.entity.Agenda;
import br.desafiomilionario.agenda.model.entity.Usuario;

public interface UsuarioService {
    UsuarioDto create(UsuarioDto dto);

    void delete(String email);

    UsuarioDto update(String email, UsuarioDto dto);

    UsuarioDto findOne(String email);

    Usuario findOneUsuario(String email);

    void associateAgenda(String email, Agenda agenda);
}
