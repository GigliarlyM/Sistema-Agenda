package br.desafiomilionario.agenda.service.impl;

import br.desafiomilionario.agenda.exception.BusinessException;
import br.desafiomilionario.agenda.model.dto.UsuarioDto;
import br.desafiomilionario.agenda.model.entity.Usuario;
import br.desafiomilionario.agenda.model.validation.Email;
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
        Email emailValidated = new Email(dto.email());
        Usuario usuario = new Usuario();
        usuario.setEmail(emailValidated.value());
        usuario.setNome(dto.nome());
        usuario.setTelefone(dto.telefone());
        usuario.setAgenda(null);
        repository.save(usuario);
        return dto;
    }

    @Override
    public void delete(String email) {
        Email emailValidated = new Email(email);
        if (!repository.existsById(emailValidated.value())) {
            throw new BusinessException("Email nao cadastrado");
        }
        repository.deleteById(email);
    }

    @Override
    public UsuarioDto update(String email, UsuarioDto dto) {
        Email emailValidated = new Email(email);
        if (!emailValidated.value().equals(dto.email())) {
            throw new BusinessException("Emails diferentes!!");
        }
        if (!repository.existsById(dto.email())) {
            throw new BusinessException("Email nao cadastrado");
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
            throw new BusinessException("Email não cadastrado!");
        }

        // @TODO: encontrar uma forma de remover esse '.orElseThrow()'
        Usuario usuario = repository.findById(email)
                .orElseThrow();

        return new UsuarioDto(
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getTelefone(),
                null
        );
    }
}
