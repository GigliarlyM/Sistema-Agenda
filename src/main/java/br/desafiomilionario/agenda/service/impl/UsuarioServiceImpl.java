package br.desafiomilionario.agenda.service.impl;

import br.desafiomilionario.agenda.model.dto.UsuarioDto;
import br.desafiomilionario.agenda.model.entity.Usuario;
import br.desafiomilionario.agenda.repository.UsuarioRepository;
import br.desafiomilionario.agenda.service.UsuarioService;
import org.springframework.stereotype.Service;

import javax.naming.directory.NoSuchAttributeException;
import java.util.NoSuchElementException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsuarioDto create(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        usuario.setNome(dto.nome());
        usuario.setTelefone(dto.telefone());
//        usuario.setAgenda(dto.agendaId());
        repository.save(usuario);
        return dto;
    }

    @Override
    public void delete(String email) {
        repository.deleteById(email);
    }

    @Override
    public UsuarioDto update(UsuarioDto dto) {
        if (!repository.existsById(dto.email())) {
            throw new NoSuchElementException("Email nao cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        usuario.setNome(dto.nome());
        usuario.setTelefone(dto.telefone());
        repository.save(usuario);
        return dto;
    }

    @Override
    public UsuarioDto findOne(String email) {
        if (!repository.existsById(email)) {
            throw new NoSuchElementException("Email não cadastrado!");
        }
        Usuario usuario = repository.findById(email)
                .orElseThrow(NoSuchElementException::new);

        UsuarioDto dto;
        try {
            dto = new UsuarioDto(
                    usuario.getEmail(),
                    usuario.getNome(),
                    usuario.getTelefone(),
                    null
            );
        } catch (NoSuchAttributeException e) {
            throw new RuntimeException(e);
        }

        return dto;
    }
}
