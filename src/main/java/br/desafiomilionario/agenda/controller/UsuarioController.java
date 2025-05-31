package br.desafiomilionario.agenda.controller;

import br.desafiomilionario.agenda.model.dto.UsuarioDto;
import br.desafiomilionario.agenda.model.validation.Email;
import br.desafiomilionario.agenda.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioDto> getUsuario(@PathVariable String email) throws Exception {
        Email emailValidated = new Email(email);
        UsuarioDto result = service.findOne(emailValidated.value());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> postUsuario(@RequestBody UsuarioDto dto) {
        UsuarioDto result = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(result.email())
                .toUri();
        return ResponseEntity.created(uri).body(result);
    }
}
