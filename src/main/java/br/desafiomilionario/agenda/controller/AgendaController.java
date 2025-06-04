package br.desafiomilionario.agenda.controller;

import br.desafiomilionario.agenda.model.dto.AgendaDto;
import br.desafiomilionario.agenda.service.AgendaService;
import br.desafiomilionario.agenda.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    private final AgendaService service;
    private final UsuarioService usuarioService;

    public AgendaController(AgendaService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<AgendaDto> createAgenda(@RequestBody AgendaDto agendaDto) {
        AgendaDto result = service.createAgenda(
                agendaDto, usuarioService.findOneUsuario(agendaDto.usuarioEmail())
        );
        // Aqui associo o usuario a agenda
        usuarioService.associateAgenda(
                agendaDto.usuarioEmail(),
                service.findOneAgenda(result.id())
        );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.id())
                .toUri();
        return ResponseEntity.created(uri).body(result);
    }
}
