package br.desafiomilionario.agenda.service.impl;

import br.desafiomilionario.agenda.model.dto.UsuarioDto;
import br.desafiomilionario.agenda.model.entity.Usuario;
import br.desafiomilionario.agenda.repository.UsuarioRepository;
import br.desafiomilionario.agenda.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsuarioDto create(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email().value());
        usuario.setNome(dto.nome());
        usuario.setTelefone(dto.telefone().value());
//        usuario.setAgenda(dto.agendaId());
        repository.save(usuario);
        return dto;
    }

    @Override
    public void delete(String email) {

    }

    @Override
    public UsuarioDto update(UsuarioDto dto) {
        return null;
    }

    @Override
    public UsuarioDto findOne(String email) {
        return null;
    }
}
