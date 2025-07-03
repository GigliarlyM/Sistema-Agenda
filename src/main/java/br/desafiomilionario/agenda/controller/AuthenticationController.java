package br.desafiomilionario.agenda.controller;

import br.desafiomilionario.agenda.config.JwtUtil;
import br.desafiomilionario.agenda.model.dto.JwtResponseDto;
import br.desafiomilionario.agenda.model.dto.LoginDto;
import br.desafiomilionario.agenda.model.dto.UsuarioDto;
import br.desafiomilionario.agenda.model.entity.Usuario;
import br.desafiomilionario.agenda.repository.UsuarioRepository;
import br.desafiomilionario.agenda.service.EmailService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {
    private final AuthenticationManager manager;
    private final UsuarioRepository repository;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager manager, UsuarioRepository repository, EmailService emailService, JwtUtil jwtUtil) {
        this.manager = manager;
        this.repository = repository;
        this.emailService = emailService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.name())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);   // opcional
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String authToken = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponseDto(authToken));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Erro de autenticacao: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UsuarioDto registration) {
        if (repository.findById(registration.email()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail ja registrado!");
        }
        if (repository.findByEmailAndNome(registration.email(), registration.nome()).isPresent()) {
            return ResponseEntity.badRequest().body("Nome de usuario ja registrado para este e-mail!");
        }

        Usuario newUser = new Usuario();
        newUser.setEmail(registration.email());
        newUser.setNome(registration.nome());
        newUser.setRoles(Collections.singleton("ROLE_USER"));
        Usuario savedUser = repository.save(newUser);
        try {
         String confirmationToken = jwtUtil.generateConfirmationToken(savedUser.getEmail());
         emailService.sendConfirmationEmail(savedUser.getEmail(), confirmationToken);

        return ResponseEntity.created(null).body("Usuario registrado com sucesso. Por favor, confirme seu e-mail!");
        } catch (MailException e) {
            repository.delete(savedUser);
            return new ResponseEntity<>("Erro ao enviar e-mail de confirmacao: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirmAccount(@RequestParam("token") String token) {
        try {
            String email = jwtUtil.extractEmail(token);
            Optional<Usuario> userOptional = repository.findById(email);

            if (userOptional.isPresent()) {
                Usuario usuario = userOptional.get();

                if (jwtUtil.validateConfirmationToken(token, usuario.getEmail()) && !usuario.isEnabled()) {
                    usuario.setEnabled(true);
                    repository.save(usuario);
                    return ResponseEntity.ok("Conta confirmada com sucesso!");
                } else if (usuario.isEnabled()) {
                    return new ResponseEntity<>("Sua conta ja esta confirmada.", HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>("Sua conta ja esta confirmada.", HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>("Usuario nao encontrado para este token.", HttpStatus.NOT_FOUND);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("Token de confirmacao expirado.", HttpStatus.BAD_REQUEST);
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return new ResponseEntity<>("Token de confirmacao invalido.", HttpStatus.BAD_REQUEST);
        }
    }
}
