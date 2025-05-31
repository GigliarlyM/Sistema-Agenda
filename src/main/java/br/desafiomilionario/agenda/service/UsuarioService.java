package br.desafiomilionario.agenda.service;

import br.desafiomilionario.agenda.model.dto.UsuarioDto;

public interface UsuarioService {
    UsuarioDto create(UsuarioDto dto);
    void delete(String email);
    UsuarioDto update(UsuarioDto dto);
    UsuarioDto findOne(String email);
}
