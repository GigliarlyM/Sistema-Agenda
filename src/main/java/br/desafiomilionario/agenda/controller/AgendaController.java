package br.desafiomilionario.agenda.controller;

import br.desafiomilionario.agenda.model.dto.AgendaDto;
import br.desafiomilionario.agenda.service.AgendaService;
import br.desafiomilionario.agenda.service.CompromissoService;
import br.desafiomilionario.agenda.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    private final AgendaService service;
    private final UsuarioService usuarioService;
    private final CompromissoService compromissoService;

    public AgendaController(
            AgendaService service,
            UsuarioService usuarioService,
            CompromissoService compromissoService
    ) {
        this.service = service;
        this.usuarioService = usuarioService;
        this.compromissoService = compromissoService;
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

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDto> searchAgenda(@PathVariable Long id) {
        AgendaDto result = service.getOneAgenda(id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenda(@PathVariable Long id) {
        compromissoService.deleteCascade(id);
        service.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }
}
