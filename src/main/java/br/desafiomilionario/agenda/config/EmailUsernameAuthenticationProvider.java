package br.desafiomilionario.agenda.config;

import br.desafiomilionario.agenda.model.entity.Usuario;
import br.desafiomilionario.agenda.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmailUsernameAuthenticationProvider implements AuthenticationProvider {
    private final UsuarioRepository repository;

    public EmailUsernameAuthenticationProvider(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String username = (String) authentication.getCredentials();
        if (email == null || username == null) {
            throw new BadCredentialsException("E-mail e nome de usuario sao obrigatorios para o login.");
        }

        Optional<Usuario> userOptional = repository.findByEmailAndNome(email, username);
        if (userOptional.isEmpty()) {
            throw new BadCredentialsException("E-mail ou nome de usuario invalidos.");
        }
        Usuario user = userOptional.get();
        if (!user.isEnabled()) {
            throw new BadCredentialsException("Sua conta nao esta ativada. Por favor, confirme seu e-maial.");
        }
        UserDetails userDetails = new User(
                user.getEmail(),
                "",
                user.isEnabled(), true, true, true,
                user.getRoles().stream()
                        .map(role -> (GrantedAuthority) () -> role)
                        .collect(Collectors.toSet())
        );
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
