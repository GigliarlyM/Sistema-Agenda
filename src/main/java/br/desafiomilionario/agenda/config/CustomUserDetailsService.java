package br.desafiomilionario.agenda.config;

import br.desafiomilionario.agenda.model.entity.Usuario;
import br.desafiomilionario.agenda.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository repository;

    public CustomUserDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = repository.findById(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado com email: " + usernameOrEmail));
        return new User(
                usuario.getEmail(),
                "",
                usuario.isEnabled(),
                true, true, true,
                usuario.getRoles().stream().map(
                        role-> (GrantedAuthority) () -> role
                ).collect(Collectors.toSet())
        );
    }
}
